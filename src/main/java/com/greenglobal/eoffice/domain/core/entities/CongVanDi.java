package com.greenglobal.eoffice.domain.core.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "congvandi")
public class CongVanDi extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(name = "soCongVan", length = 20, nullable = false)
  private String soCongVan;

  @Column(name = "ngayCongVan", nullable = false)
  private Date ngayCongVan;

  @Column(name = "noiDung", length = 5000, nullable = true)
  private String noiDung;

  @Column(name = "trichYeu", length = 5000, nullable = true)
  private String trichYeu;

  @Column(name = "trangThai", nullable = false)
  private int trangThai;

  /**
   * @return the soCongVan
   */
  public String getSoCongVan() {
    return soCongVan;
  }

  /**
   * @param soCongVan the soCongVan to set
   */
  public void setSoCongVan(String soCongVan) {
    this.soCongVan = soCongVan;
  }

  /**
   * @return the trichYeu
   */
  public String getTrichYeu() {
    return trichYeu;
  }

  /**
   * @param trichYeu the trichYeu to set
   */
  public void setTrichYeu(String trichYeu) {
    this.trichYeu = trichYeu;
  }

  /**
   * @return the noiDung
   */
  public String getNoiDung() {
    return noiDung;
  }

  /**
   * @param noiDung the noiDung to set
   */
  public void setNoiDung(String noiDung) {
    this.noiDung = noiDung;
  }

  /**
   * @return the ngayCongVan
   */
  public Date getNgayCongVan() {
    return ngayCongVan;
  }

  /**
   * @param ngayCongVan the ngayCongVan to set
   */
  public void setNgayCongVan(Date ngayCongVan) {
    this.ngayCongVan = ngayCongVan;
  }

  /**
   * @return the trangThai
   */
  public int getTrangThai() {
    return trangThai;
  }

  /**
   * @param trangThai the trangThai to set
   */
  public void setTrangThai(int trangThai) {
    this.trangThai = trangThai;
  }
}