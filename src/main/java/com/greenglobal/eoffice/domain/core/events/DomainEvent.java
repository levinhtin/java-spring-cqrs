package com.greenglobal.eoffice.domain.core.events;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * this is base domain event
 */
public abstract class DomainEvent implements Serializable {
  @JsonIgnore
  private static final long serialVersionUID = 1L;
  
  @JsonIgnore
  private final String eventId;
  
  @JsonIgnore
  private final String eventType;

  @JsonIgnore
  private final Instant timestamp = Instant.now();
  
  @JsonIgnore
  private final String aggregateType;

  @JsonIgnore
  private final String aggregateId;
  // private final Long timestamp = System.currentTimeMillis();

  public DomainEvent(String eventType, String aggregateType, String aggregateId) {
    this(UUID.randomUUID().toString(), eventType, aggregateType, aggregateId);
  }

  public DomainEvent(final String eventId, final String eventType, final String aggregateType, final String aggregateId) {
    this.eventId = eventId;
    this.eventType = eventType;
    this.aggregateType = aggregateType;
    this.aggregateId = aggregateId;
  }

  public String getEventId() {
    return this.eventId;
  }

  public Instant getTimestamp() {
    return this.timestamp;
  }

  public String getEventType() {
    return this.eventType;
  }

  @JsonIgnore
  public String getAggregateType() {
    return this.aggregateType;
  }

  public String getAggregateId() {
    return this.aggregateId;
  }
}