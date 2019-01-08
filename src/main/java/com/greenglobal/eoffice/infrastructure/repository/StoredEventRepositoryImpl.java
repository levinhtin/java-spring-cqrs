package com.greenglobal.eoffice.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.greenglobal.eoffice.domain.core.entities.StoredEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoredEventRepositoryImpl implements StoredEventRepository {
  
  @Autowired
  private EntityManager em;
  
  @Transactional
  public String create(StoredEvent entity) {
    try {
      em.persist(entity);
      return entity.getId();
    } catch(Exception ex) {
      throw ex;
    }
  }
}