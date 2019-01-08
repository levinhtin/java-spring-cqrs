package com.greenglobal.eoffice.domain.application.congvandi.commands.update;

import com.greenglobal.eoffice.domain.core.entities.ItemCommand;

/**
 * Ký duyệt công văn đi
 */
public class KyDuyetCongVanDiCmd extends ItemCommand {
    /**
     * Trạng thái ký duyệt
     */
    private int trangThai;

    /**
     * Get trangThai ký duyệt.
     * @return 
     * 2: Lãnh đạo phòng ban chủ trì ký duyệt.
     * 3: Lãnh đạo phòng ban phối hợp ký duyệt.
     * 4: Thư ký ký duyệt.
     * 5: Lãnh đạo đơn vị ký duyệt.
     */
    public int getTrangThai() {
        return this.trangThai;
    }
}