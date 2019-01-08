package com.greenglobal.eoffice.domain.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "storedevent")
public class StoredEvent {

  @Id
  @Column(name = "id", unique = true, length = 48, nullable = false)
  private String id;

  @Column(name = "aggregateId", length = 48, nullable = false)
  private String aggregateId;

  @Column(name = "aggregateType", length = 30, nullable = false)
  private String aggregateType;

  @Column(name = "content", length = 500, nullable = false)
  private String content;

  @Column(name = "timestamp", nullable = false)
  private Instant timestamp;

  @Column(name = "eventType", length = 100, nullable = false)
  private String eventType;

  public StoredEvent(String id, String eventType, Instant timestamp, String aggregateId, String aggregateType, String content) {
    this.id = id;
    this.eventType = eventType;
    this.timestamp = timestamp;
    this.aggregateId = aggregateId;
    this.aggregateType = aggregateType;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public String getAggregateId() {
    return aggregateId;
  }

  public String getEventType() {
    return eventType;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public String getAggregateType() {
    return aggregateType;
  }

  public String getContent() {
    return content;
  }
}