package com.greenglobal.eoffice;

import java.util.HashMap;
import java.util.Map;

import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.infrastructure.broker.EventDeserializer;
import com.greenglobal.eoffice.infrastructure.broker.EventSerializer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

@EnableKafka
@Configuration
public class SpringKafkaConfig {
  private static final String broker = "192.168.1.233:9092";
  private static final String groupId = "CongVanDi";

  @Bean
    public ProducerFactory<String, DomainEvent> producerFactory() {
      Map<String, Object> props = new HashMap<>();
      props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker);
      props.put(ProducerConfig.CLIENT_ID_CONFIG, "congvandi_service");
      props.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, 1000);
      props.put(ProducerConfig.ACKS_CONFIG, "all");
      props.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
      props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EventSerializer.class.getName());
  
      var factory = new DefaultKafkaProducerFactory<String, DomainEvent>(props);
      // factory.setTransactionIdPrefix("congvandi.");
        
      return factory;
    }

  @Bean
  public ConsumerFactory<String, DomainEvent> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, broker);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EventDeserializer.class.getName());
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
    // props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    // props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    // props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, DomainEvent> kafkaListenerContainerFactory() {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, DomainEvent>();
    factory.setConsumerFactory(consumerFactory());
    factory.setConcurrency(3);
    
    return factory;
  }

  // @Bean
  // public KafkaTransactionManager<String, DomainEvent> kafkaTransactionManager(){
  //     KafkaTransactionManager<String, DomainEvent> transactionManager = new KafkaTransactionManager<>(producerFactory());
  //     return transactionManager;
  // }

  @Bean
  public KafkaTemplate<String, DomainEvent> getKafkaTemplate(@Autowired ProducerFactory<String, DomainEvent> factory) {
    return new KafkaTemplate<>(factory);
  }
}