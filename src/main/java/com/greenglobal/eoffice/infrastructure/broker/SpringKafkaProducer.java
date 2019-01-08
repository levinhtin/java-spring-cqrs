package com.greenglobal.eoffice.infrastructure.broker;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.infrastructure.configs.IKafkaConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

public class SpringKafkaProducer implements EventProducer {

    @Autowired
    private KafkaTemplate<String, DomainEvent> kafkaTemplate;

    public void publish(DomainEvent event) {
        // execute transaction conflict with SQL transction in repository
        // kafkaTemplate.executeInTransaction(operations -> {
        //     var topic = this.getTopic(event);
        //     final String key = event.getEventId();
        //     operations
        //         .send(topic, key, event)
        //         .addCallback(this::onSuccess, this::onFailure);
        //     return true;
        // });

        var topic = this.getTopic(event);
        final String key = event.getEventId();
        kafkaTemplate
            .send(topic, key, event)
            .addCallback(this::onSuccess, this::onFailure);
    }

    private void onSuccess(final SendResult<String, DomainEvent> result) {
        System.out.printf("DomainEvent %s has been written to topic-partition %s-%s with ingestion timestamp %d.\n",
                result.getProducerRecord().key(),
                result.getRecordMetadata().topic(),
                result.getRecordMetadata().partition(),
                result.getRecordMetadata().timestamp());
    }

    private void onFailure(final Throwable t) {
        System.out.printf("Unable to write DomainEvent to topic %s.", t.getMessage());
    }

    private String getTopic(DomainEvent event) {
        switch (event.getEventType()) {
            case "CongVanDiCreated":
            case "CongVanDiRequestSent":
            case "CongVanDiApproved":
            case "CongVanDiDenied":
                return IKafkaConstants.TOPIC_CONGVANDI;
            default:
                throw new IllegalArgumentException("No topic mapped");
        }
    }
}