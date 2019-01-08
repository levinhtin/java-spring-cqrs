package com.greenglobal.eoffice.infrastructure.broker;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.infrastructure.event.EventHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@Service
public class SpringKafkaConsumer {

  @Autowired
  private EventHandler eventHandler;

  @KafkaListener(topics = "${eoffice.kafka.topic}", containerGroup = "CongVanDi", concurrency = "3")
  public void consume(final DomainEvent event) {
    try {
      eventHandler.onEvent(event);
      System.out.printf("Consume event: %s\n", new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    } catch (Exception e) {
      // log the exception and do *not* acknowledge the event
      System.out.printf("Unable to apply event {} to the latest state of aggregate with ID {}.", event, event.getEventId(), e);
    }
  }
}
