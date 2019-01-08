package com.greenglobal.eoffice.domain.application.congvandi.events.entities;

import com.greenglobal.eoffice.domain.core.constants.EventAggregateType;
import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.domain.core.events.IEventType;

public class CongVanDiDenied extends DomainEvent {
  private static final long serialVersionUID = 1L;
  private int id;
  private String noiDung;

  public CongVanDiDenied(final int id, final String noiDung) {
    super(IEventType.CongVanDiDenied, EventAggregateType.CongVanDi, Integer.toString(id));
    this.id = id;
    this.noiDung = noiDung;
  }
  
  /**
   * @return the noiDung
   */
  public String getNoiDung() {
    return noiDung;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }
}