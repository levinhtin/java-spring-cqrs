package com.greenglobal.eoffice.infrastructure.broker;

import java.util.Map;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.util.SerializationUtils;

public class EventSerializer implements Serializer<DomainEvent> {

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public byte[] serialize(final String topic, final DomainEvent event) {
        try {
            if (event == null)
                return null;

            // ObjectMapper objectMapper = new ObjectMapper();
            // return objectMapper.writeValueAsBytes(event);
            byte[] data = SerializationUtils.serialize(event);
            return data;
        } catch (Exception e) {
            throw new SerializationException("Could not serialize event", e);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }

}