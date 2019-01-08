package com.greenglobal.eoffice.domain.application.congvandi.queries;

import java.util.ArrayList;
import java.util.List;

import com.greenglobal.eoffice.domain.application.congvandi.queries.paging.CongVanDiPagingDTO;
import com.greenglobal.eoffice.domain.application.congvandi.queries.paging.GetAllCongVanDiQuery;
import com.greenglobal.eoffice.infrastructure.repository.CongVanDiRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CongVanDiQueryHandlerImpl implements CongVanDiQueryHandler {

    @Autowired
    private CongVanDiRepository congVanDiRepo;

    /**
     * Get paging CongVanDi.
     */
    public List<CongVanDiPagingDTO> handle(GetAllCongVanDiQuery query) {
        var data = this.congVanDiRepo.getAll(query.getPage(), query.getLimit(), query.getTrangThai());

        var result = new ArrayList<CongVanDiPagingDTO>();
        for (var item : data) {
            result.add(new CongVanDiPagingDTO(
                    item.getId(), 
                    item.getSoCongVan(), 
                    item.getNgayCongVan(),
                    item.getTrichYeu()));
        }

        return result;
    }
}