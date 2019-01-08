package com.greenglobal.eoffice.domain.application.congvandi.commands;

import static java.util.Collections.singletonList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.greenglobal.eoffice.domain.application.congvandi.commands.create.CreateCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.HuyDuyetCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.KyDuyetCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.TrinhCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiApproved;
import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiCreated;
import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiDenied;
import com.greenglobal.eoffice.domain.application.congvandi.events.entities.CongVanDiRequestSent;
import com.greenglobal.eoffice.domain.core.entities.CongVanDi;
import com.greenglobal.eoffice.domain.core.events.DomainEvent;
import com.greenglobal.eoffice.infrastructure.broker.EventProducer;
import com.greenglobal.eoffice.infrastructure.repository.CongVanDiRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CongVanDiCmdHandlerImpl implements CongVanDiCmdHandler {

    @Autowired
    private EventProducer producer;

    @Autowired
    private CongVanDiRepository congVanDiRepo;

    /**
     * Tạo mới CongVanDi command.
     */
    public void handle(CreateCongVanDiCmd cmd) {
        var now = new Date();

        var entity = new CongVanDi();
        entity.setSoCongVan(cmd.getSoCongVan());
        entity.setNgayCongVan(cmd.getNgayCongVan());
        entity.setNoiDung(cmd.getNoiDung());
        entity.setTrichYeu(cmd.getTrichYeu());
        entity.setTrangThai(0);
        entity.setCreatedAt(now);
        entity.setModifiedAt(now);
        entity.setIsDeleted(false);

        this.congVanDiRepo.create(entity);
        producer.publish(new CongVanDiCreated(entity));

        System.out.printf("Publish event %s: %s\n", CongVanDiCreated.class.getSimpleName(),
                new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    }

    /**
     * Trình CongVanDi command
     */
    public void handle(TrinhCongVanDiCmd cmd) {
        var now = new Date();

        var entity = this.congVanDiRepo.get(cmd.getId());
        entity.setTrangThai(cmd.getTrangThai());
        entity.setModifiedAt(now);
        this.congVanDiRepo.update(entity);

        var event = new CongVanDiRequestSent(cmd.getId(), cmd.getTrangThai());
        producer.publish(event);

        System.out.printf("Publish event %s: %s\n", CongVanDiRequestSent.class.getSimpleName(),
                new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    }

    /**
     * Ký duyệt CongVanDi command
     */
    public void handle(KyDuyetCongVanDiCmd cmd) {
        var now = new Date();

        var entity = this.congVanDiRepo.get(cmd.getId());
        entity.setTrangThai(cmd.getTrangThai());
        entity.setModifiedAt(now);
        this.congVanDiRepo.update(entity);

        var event = new CongVanDiApproved(cmd.getId(), cmd.getTrangThai());
        producer.publish(event);

        System.out.printf("Publish event %s: %s\n", CongVanDiApproved.class.getSimpleName(),
                new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    }

    /**
     * Huỷ duyệt CongVanDi command
     */
    public void handle(HuyDuyetCongVanDiCmd cmd) {
        var now = new Date();

        var entity = this.congVanDiRepo.get(cmd.getId());
        entity.setTrangThai(0);
        entity.setModifiedAt(now);
        this.congVanDiRepo.update(entity);

        var event = new CongVanDiDenied(cmd.getId(), cmd.getNoiDung());
        producer.publish(event);

        System.out.printf("Publish event %s: %s\n", CongVanDiDenied.class.getSimpleName(),
                new SimpleDateFormat("dd-mm-yyyy hh:mm:ss.SSS").format(new Date()));
    }

    private List<DomainEvent> validate(final CongVanDi entity) {
        return singletonList(new CongVanDiCreated(entity));
    }
}