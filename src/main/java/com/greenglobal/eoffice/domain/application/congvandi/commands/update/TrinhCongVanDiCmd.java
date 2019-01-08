package com.greenglobal.eoffice.domain.application.congvandi.commands.update;

import com.greenglobal.eoffice.domain.core.entities.ItemCommand;

/**
 * Trình công văn đi
 */
public class TrinhCongVanDiCmd extends ItemCommand {
    /**
     * Trạng thái == 1
     * @return
     */
    public int getTrangThai() {
        return 1;
    }
}