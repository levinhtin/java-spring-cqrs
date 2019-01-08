
package com.greenglobal.eoffice.infrastructure.broker;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.infrastructure.configs.IKafkaConstants;
import com.greenglobal.eoffice.infrastructure.event.EventHandler;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Collections;

public class KafkaEventConsumer implements EventConsumer {

    private final KafkaConsumer<String, DomainEvent> consumer;
    private final AtomicBoolean closed = new AtomicBoolean();

    @Autowired
    private EventHandler eventHandler;

    public KafkaEventConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, IKafkaConstants.KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, IKafkaConstants.GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EventDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, IKafkaConstants.MAX_POLL_RECORDS);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, IKafkaConstants.OFFSET_RESET_EARLIER);

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(IKafkaConstants.TOPIC_CONGVANDI));
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                consume();
            }
        } catch (WakeupException e) {
            // will wakeup for closing
        } finally {
            consumer.close();
        }
    }

    private void consume() {
        ConsumerRecords<String, DomainEvent> records = consumer.poll(Duration.ZERO);
        
        for (ConsumerRecord<String, DomainEvent> record : records) {
            eventHandler.onEvent(record.value());
        }
        consumer.commitSync();
    }

    public void stop() {
        closed.set(true);
        consumer.wakeup();
    }
}