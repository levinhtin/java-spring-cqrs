package com.greenglobal.eoffice.infrastructure.event;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;

public interface EventHandler {
  void onEvent(DomainEvent event);
}