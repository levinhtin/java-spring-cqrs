package com.greenglobal.eoffice.domain.application.congvandi.commands;

import com.greenglobal.eoffice.domain.application.congvandi.commands.create.CreateCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.HuyDuyetCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.KyDuyetCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.TrinhCongVanDiCmd;

public interface CongVanDiCmdHandler {
    void handle(CreateCongVanDiCmd cmd);
    void handle(TrinhCongVanDiCmd cmd);
    void handle(KyDuyetCongVanDiCmd cmd);
    void handle(HuyDuyetCongVanDiCmd cmd);
}