package com.greenglobal.eoffice.domain.application.congvandi.commands.update;

import com.greenglobal.eoffice.domain.core.entities.ItemCommand;

/**
 * Lãnh đạo đơn vị ký duyệt công văn đi
 */
public class HuyDuyetCongVanDiCmd extends ItemCommand {
    /**
     * Lý do từ chối duyệt công văn
     */
    private String noiDung;

    /**
     * Get noiDung.
     * @return string.
     */
    public String getNoiDung() {
        return this.noiDung;
    }
}