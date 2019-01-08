package com.greenglobal.eoffice.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.greenglobal.eoffice.domain.core.entities.CongVanDi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CongVanDiRepositoryImpl implements CongVanDiRepository {

    @Autowired
    private EntityManager em;

    public CongVanDi get(Integer id) {
        return em.find(CongVanDi.class, id);
    }

    public List<CongVanDi> getAll(int page, int limit, int trangThai) {
        var offset = (page - 1) * limit;
        var result = em.createNativeQuery(
                "SELECT * from CongVanDi WHERE trangThai = :trangThai ORDER BY createdAt DESC LIMIT :limit OFFSET :offset",
                CongVanDi.class)
                .setParameter("trangThai", trangThai)
                .setParameter("offset", offset)
                .setParameter("limit", limit)
                .getResultList();

        return result;
    }

    @Transactional
    public Integer create(CongVanDi entity) {
        try {
            em.persist(entity);

            // var sql = "insert into congvandi (soCongVan, ngayCongVan, noiDung, trichYeu,
            // trangThai, createdAt, modifiedAt, deletedAt, isDeleted)"
            // + "Values(:soCongVan, :ngayCongVan, :noiDung, :trichYeu, :trangThai,
            // :createdAt, :modifiedAt, :deletedAt, :isDeleted)";

            // var result = em.createNativeQuery(sql)
            // .setParameter("soCongVan", entity.getSoCongVan())
            // .setParameter("ngayCongVan", entity.getNgayCongVan())
            // .setParameter("noiDung", entity.getNoiDung())
            // .setParameter("trichYeu", entity.getTrichYeu())
            // .setParameter("trangThai", entity.getTrangThai())
            // .setParameter("createdAt", entity.getCreatedAt())
            // .setParameter("modifiedAt", entity.getModifiedAt())
            // .setParameter("deletedAt", entity.getDeletedAt())
            // .setParameter("isDeleted", entity.getIsDeleted())
            // .getSingleResult();

            return entity.getId();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Transactional
    public CongVanDi update(CongVanDi entity) {
        var updated = em.merge(entity);
        return updated;
    }
}