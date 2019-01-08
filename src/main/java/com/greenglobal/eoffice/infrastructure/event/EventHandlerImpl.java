package com.greenglobal.eoffice.infrastructure.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiApproved;
import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiCreated;
import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiDenied;
import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiRequestSent;
import com.greenglobal.eoffice.domain.core.entities.StoredEvent;
import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.infrastructure.repository.StoredEventRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class EventHandlerImpl implements EventHandler {

    @Autowired
    private StoredEventRepository storedEventRepo;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof DomainEvent) {
            System.out.printf("On event Domain created %s\n", event.getEventId());
        }

        /**
         * Event sourcing.
         */
        if (event instanceof CongVanDiCreated) {
            this.onEvent((CongVanDiCreated) event);
        } else if (event instanceof CongVanDiRequestSent) {
            this.onEvent((CongVanDiRequestSent) event);
        } else if (event instanceof CongVanDiApproved) {
            this.onEvent((CongVanDiApproved) event);
        } else if (event instanceof CongVanDiDenied) {
            this.onEvent((CongVanDiDenied) event);
        }
    };

    private void onEvent(CongVanDiCreated event) {
        var data = event.getCongVanDi();
        var objectMapper = new ObjectMapper();

        String content = null;
        try {
            content = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        var storedEvent = new StoredEvent(
            event.getEventId(),
            event.getEventType(),
            event.getTimestamp(),
            event.getAggregateId(),
            event.getAggregateType(),
            content);

        storedEventRepo.create(storedEvent);

        System.out.printf("Event stored %s: %s\n", CongVanDiCreated.class.getSimpleName(), new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    }

    private void onEvent(CongVanDiRequestSent event) {
        var objectMapper = new ObjectMapper();

        String content = null;
        try {
            content = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        var storedEvent = new StoredEvent(
            event.getEventId(),
            event.getEventType(),
            event.getTimestamp(),
            event.getAggregateId(),
            event.getAggregateType(),
            content);

        storedEventRepo.create(storedEvent);

        System.out.printf("Event stored %s: %s\n", CongVanDiRequestSent.class.getSimpleName(), new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    }

    private void onEvent(CongVanDiApproved event) {
        var objectMapper = new ObjectMapper();

        String content = null;
        try {
            content = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        var storedEvent = new StoredEvent(
            event.getEventId(),
            event.getEventType(),
            event.getTimestamp(),
            event.getAggregateId(),
            event.getAggregateType(),
            content);

        storedEventRepo.create(storedEvent);

        System.out.printf("Event stored %s: %s\n", CongVanDiApproved.class.getSimpleName(), new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    }

    private void onEvent(CongVanDiDenied event) {
        var objectMapper = new ObjectMapper();

        String content = null;
        try {
            content = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        var storedEvent = new StoredEvent(
            event.getEventId(),
            event.getEventType(),
            event.getTimestamp(),
            event.getAggregateId(),
            event.getAggregateType(),
            content);

        storedEventRepo.create(storedEvent);

        System.out.printf("Event stored %s: %s\n", CongVanDiDenied.class.getSimpleName(), new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    }
}