package com.greenglobal.eoffice.infrastructure.broker;

import java.util.Properties;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.infrastructure.configs.IKafkaConstants;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaEventProducer implements EventProducer {

    private Producer<String, DomainEvent> producer;
    private String topic;

    @PostConstruct
    public void init() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.233:9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, IKafkaConstants.CLIENT_ID);
        props.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, 1000);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSerializer.class.getName());

        producer = new KafkaProducer<>(props);
    }

    public void publish(DomainEvent event) {
        try {
            this.topic = this.getTopic(event);
            final ProducerRecord<String, DomainEvent> record = new ProducerRecord<String, DomainEvent>(topic, event);
            var testCallBack = new TestCallback();

            producer.send(record, testCallBack);
        } catch (ProducerFencedException e) {
            producer.close();
        } catch (KafkaException e) {
            producer.abortTransaction();
        }
    }

    @PreDestroy
    public void close() {
        producer.close();
    }

    private static class TestCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                System.out.println("Error while producing message to topic :" + recordMetadata);
                e.printStackTrace();
            } else {
                String message = String.format("Producer sent message to topic:%s partition:%s  offset:%s", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                System.out.println(message);
            }
        }
    }

    private String getTopic(DomainEvent event) {
        switch (event.getEventType()) {
            case "CongVanDiCreated":
                return IKafkaConstants.TOPIC_CONGVANDI;
            default:
                throw new IllegalArgumentException("No topic mapped");
        }
    }
}