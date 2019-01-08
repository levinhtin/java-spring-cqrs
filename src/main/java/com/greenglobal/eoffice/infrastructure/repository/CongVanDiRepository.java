package com.greenglobal.eoffice.infrastructure.repository;

import java.util.List;

import com.greenglobal.eoffice.domain.core.entities.CongVanDi;

public interface CongVanDiRepository {
  CongVanDi get(Integer id);
  List<CongVanDi> getAll(int page, int limit, int trangThai);
  Integer create(CongVanDi entity);
  CongVanDi update(CongVanDi entity);
}