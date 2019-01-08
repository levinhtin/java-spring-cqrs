package com.greenglobal.eoffice.api.controllers;

import java.net.URI;
import java.util.List;

import com.greenglobal.eoffice.domain.application.congvandi.commands.CongVanDiCmdHandler;
import com.greenglobal.eoffice.domain.application.congvandi.commands.create.CreateCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.HuyDuyetCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.KyDuyetCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.commands.update.TrinhCongVanDiCmd;
import com.greenglobal.eoffice.domain.application.congvandi.queries.CongVanDiQueryHandler;
import com.greenglobal.eoffice.domain.application.congvandi.queries.paging.CongVanDiPagingDTO;
import com.greenglobal.eoffice.domain.application.congvandi.queries.paging.GetAllCongVanDiQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CongVanDi API.
 */
@RestController
@RequestMapping(value = { "/api" })
public class CongVanDiController {

    @Autowired
    private CongVanDiCmdHandler congVanDiCmdHandler;
    
    @Autowired
    private CongVanDiQueryHandler congVanDiQueryHandler;

    @GetMapping("/congvandi")
    public ResponseEntity<List<CongVanDiPagingDTO>> getAllPaging(
        @RequestParam("page") int page, 
        @RequestParam("limit") int limit, 
        @RequestParam("trangThai") int trangThai) {
            var data = congVanDiQueryHandler.handle(new GetAllCongVanDiQuery(page, limit, trangThai));
            return ResponseEntity.ok(data);
    }

    /**
     * Create new CongVanDi.
     * 
     * @return Uri create CongVanDi
     */
    @PostMapping("/congvandi")
    public ResponseEntity<String> post(@RequestBody CreateCongVanDiCmd command) {
        this.congVanDiCmdHandler.handle(command);

        var uri = URI.create("/api/congvandi");
        return ResponseEntity.created(uri).build();
    }

    /**
     * Trình CongVanDi.
     * 
     * @return Is CongVanDi updated
     */
    @PutMapping("/congvandi/{id}/trinh")
    public ResponseEntity<Boolean> trinh(@PathVariable("id") int id, @RequestBody TrinhCongVanDiCmd command) {
        if (id == command.getId()) {
            this.congVanDiCmdHandler.handle(command);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    /**
     * Ký duyệt CongVanDi.
     * 
     * @return Is CongVanDi updated
     */
    @PutMapping("/congvandi/{id}/kyduyet")
    public ResponseEntity<Boolean> kyDuyet(@PathVariable("id") int id, @RequestBody KyDuyetCongVanDiCmd command) {
        if (id == command.getId()) {
            this.congVanDiCmdHandler.handle(command);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    /**
     * Huỷ duyệt CongVanDi.
     * 
     * @return Is CongVanDi updated
     */
    @PutMapping("/congvandi/{id}/huyduyet")
    public ResponseEntity<Boolean> huyDuyet(@PathVariable("id") int id, @RequestBody HuyDuyetCongVanDiCmd command) {
        if (id == command.getId()) {
            this.congVanDiCmdHandler.handle(command);
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }
}