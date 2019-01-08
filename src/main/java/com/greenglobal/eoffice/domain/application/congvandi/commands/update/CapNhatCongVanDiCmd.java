package com.greenglobal.eoffice.domain.application.congvandi.commands.update;

import com.greenglobal.eoffice.domain.core.entities.ItemCommand;

/**
 * Cập nhật nội dung công văn
 */
public class CapNhatCongVanDiCmd extends ItemCommand {
    
    private String soCongVan;
    private String noiDung;
    private String trichYeu;

    /**
     * Get soCongVan.
     * @return số công văn.
     */
    public String getSoCongVan() {
        return this.soCongVan;
    }

    /**
     * Get noiDung.
     * @return nội dung công văn.
     */
    public String getNoiDung() {
        return this.noiDung;
    }

    /**
     * Get trichYeu.
     * @return trích yếu.
     */
    public String getTrichYeu() {
        return this.trichYeu;
    }
}