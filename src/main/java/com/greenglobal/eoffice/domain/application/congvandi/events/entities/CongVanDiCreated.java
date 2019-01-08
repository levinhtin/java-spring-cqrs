package com.greenglobal.eoffice.domain.application.congvandi.events.entities;

import com.greenglobal.eoffice.domain.core.constants.EventAggregateType;
import com.greenglobal.eoffice.domain.core.entities.CongVanDi;
import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.domain.core.events.IEventType;

public class CongVanDiCreated extends DomainEvent {
  private static final long serialVersionUID = 1L;
  private CongVanDi congVanDi;

  public CongVanDiCreated(final CongVanDi congVanDi) {
    super(IEventType.CongVanDiCreated, EventAggregateType.CongVanDi, Integer.toString(congVanDi.getId()));
    this.congVanDi = congVanDi;
  }

  public CongVanDi getCongVanDi() {
    return congVanDi;
  }
}