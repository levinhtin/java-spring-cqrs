package com.greenglobal.eoffice.infrastructure.repository;

import com.greenglobal.eoffice.domain.core.entities.StoredEvent;

public interface StoredEventRepository {
  String create(StoredEvent entity);
}