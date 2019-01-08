package com.greenglobal.eoffice.infrastructure.broker;

import java.util.Map;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.util.SerializationUtils;

public class EventDeserializer implements Deserializer<DomainEvent> {

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public DomainEvent deserialize(final String topic, final byte[] data) {
        try {
            if (data == null)
                return null;

            var event = (DomainEvent)SerializationUtils.deserialize(data);
            return event;
        } catch (Exception e) {
            throw new SerializationException("Could not deserialize event", e);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}