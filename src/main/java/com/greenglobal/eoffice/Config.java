package com.greenglobal.eoffice;

import com.greenglobal.eoffice.domain.application.congvandi.commands.CongVanDiCmdHandler;
import com.greenglobal.eoffice.domain.application.congvandi.commands.CongVanDiCmdHandlerImpl;
import com.greenglobal.eoffice.domain.application.congvandi.events.CongVanDiEventHandler;
import com.greenglobal.eoffice.domain.application.congvandi.events.CongVanDiEventHandlerImpl;
import com.greenglobal.eoffice.domain.application.congvandi.queries.CongVanDiQueryHandler;
import com.greenglobal.eoffice.domain.application.congvandi.queries.CongVanDiQueryHandlerImpl;
import com.greenglobal.eoffice.infrastructure.event.EventHandler;
import com.greenglobal.eoffice.infrastructure.event.EventHandlerImpl;
import com.greenglobal.eoffice.infrastructure.broker.EventProducer;
import com.greenglobal.eoffice.infrastructure.broker.SpringKafkaProducer;
import com.greenglobal.eoffice.infrastructure.repository.CongVanDiRepository;
import com.greenglobal.eoffice.infrastructure.repository.CongVanDiRepositoryImpl;
import com.greenglobal.eoffice.infrastructure.repository.StoredEventRepository;
import com.greenglobal.eoffice.infrastructure.repository.StoredEventRepositoryImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public EventProducer eventProducer() {
    return new SpringKafkaProducer();
  }

  // @Bean
  // public EventConsumer eventConsumer() {
  //   var r = new KafkaEventConsumer();
  //   Thread thread1 = new Thread(r, "Event Consumer");
  //   thread1.start();
  //   return r;
  // }

  @Bean
  public EventHandler eventHandler() {
    return new EventHandlerImpl();
  }

  @Bean
  public CongVanDiEventHandler congVanDiEventHandler() {
    return new CongVanDiEventHandlerImpl();
  }

  @Bean
  public CongVanDiRepository congVanDiRepo() {
    return new CongVanDiRepositoryImpl();
  }

  @Bean
  public StoredEventRepository storedEventRepo() {
    return new StoredEventRepositoryImpl();
  }

  @Bean
  public CongVanDiCmdHandler congVanDiCmdHanler() {
    return new CongVanDiCmdHandlerImpl();
  }

  @Bean
  public CongVanDiQueryHandler congVanDiQueryHanler() {
    return new CongVanDiQueryHandlerImpl();
  }
}