package com.greenglobal.eoffice.domain.application.congvandi.queries.paging;

import java.util.Date;

public class CongVanDiPagingDTO {
    private int id;
    private String soCongVan;
    private Date ngayCongVan;
    private String trichYeu;

    public CongVanDiPagingDTO(int id, String soCongVan, Date ngayCongVan, String trichYeu) {
        this.id = id;
        this.soCongVan = soCongVan;
        this.ngayCongVan = ngayCongVan;
        this.trichYeu = trichYeu;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the trichYeu
     */
    public String getTrichYeu() {
        return trichYeu;
    }

    /**
     * @return the ngayCongVan
     */
    public Date getNgayCongVan() {
        return ngayCongVan;
    }

    /**
     * @return the soCongVan
     */
    public String getSoCongVan() {
        return soCongVan;
    }
}