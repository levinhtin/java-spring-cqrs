package com.greenglobal.eoffice.domain.application.congvandi.events.entities;

import com.greenglobal.eoffice.domain.core.constants.EventAggregateType;
import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.domain.core.events.IEventType;

public class CongVanDiRequestSent extends DomainEvent {
  private static final long serialVersionUID = 1L;
  private int id;
  private int trangThai;

  public CongVanDiRequestSent(final int id, final int trangThai) {
    super(IEventType.CongVanDiRequestSent, EventAggregateType.CongVanDi, Integer.toString(id));
    this.id = id;
    this.trangThai = trangThai;
  }

  /**
   * @return the trangThai
   */
  public int getTrangThai() {
    return trangThai;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }
}