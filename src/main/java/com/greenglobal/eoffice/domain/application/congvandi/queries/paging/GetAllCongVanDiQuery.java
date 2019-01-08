package com.greenglobal.eoffice.domain.application.congvandi.queries.paging;

public class GetAllCongVanDiQuery {
    private int page = 1;
    private int limit = 10;
    private int trangThai;

    public GetAllCongVanDiQuery(int page, int limit, int trangThai) {
        this.page = page;
        this.limit = limit;
        this.trangThai = trangThai;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return this.page;
    }

    /**
     * @return the trangThai
     */
    public int getTrangThai() {
        return this.trangThai;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return this.limit;
    }
}