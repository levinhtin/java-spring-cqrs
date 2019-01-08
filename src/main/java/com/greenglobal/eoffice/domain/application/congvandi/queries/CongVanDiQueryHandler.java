package com.greenglobal.eoffice.domain.application.congvandi.queries;

import java.util.List;

import com.greenglobal.eoffice.domain.application.congvandi.queries.paging.CongVanDiPagingDTO;
import com.greenglobal.eoffice.domain.application.congvandi.queries.paging.GetAllCongVanDiQuery;

public interface CongVanDiQueryHandler {
    List<CongVanDiPagingDTO> handle(GetAllCongVanDiQuery query);
}