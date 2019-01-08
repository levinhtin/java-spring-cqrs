package com.greenglobal.eoffice.domain.application.congvandi.events;

import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiCreated;
import com.greenglobal.eoffice.infrastructure.event.EventHandler;

public interface CongVanDiEventHandler extends EventHandler {
    void onEvent(CongVanDiCreated event);
}