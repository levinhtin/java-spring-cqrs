package com.greenglobal.eoffice.domain.application.congvandi.events;

import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiCreated;
import com.greenglobal.eoffice.infrastructure.event.EventHandlerImpl;

public class CongVanDiEventHandlerImpl extends EventHandlerImpl implements CongVanDiEventHandler {

  @Override
  public void onEvent(CongVanDiCreated event) {
    if (event instanceof CongVanDiCreated) {
      System.out.printf("On event CongVanDi created %s\n", event.getEventId());
    }
  };
}