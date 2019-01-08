package com.greenglobal.eoffice.infrastructure.broker;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;

public interface EventProducer {
    void publish(DomainEvent event);
}