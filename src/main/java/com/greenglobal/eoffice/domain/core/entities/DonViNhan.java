package com.greenglobal.eoffice.domain.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "donvinhan")
public class DonViNhan extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(name = "donViNhanId", nullable = false)
  private int donViNhanId;

  @Column(name = "congVanDiId", nullable = false)
  private int congVanDiId;

  /**
   * @return the congVanDiId
   */
  public int getCongVanDiId() {
    return congVanDiId;
  }

  /**
   * @param congVanDiId the congVanDiId to set
   */
  public void setCongVanDiId(int congVanDiId) {
    this.congVanDiId = congVanDiId;
  }

  /**
   * @return the donViNhanId
   */
  public int getDonViNhanId() {
    return donViNhanId;
  }

  /**
   * @param donViNhanId the donViNhanId to set
   */
  public void setDonViNhanId(int donViNhanId) {
    this.donViNhanId = donViNhanId;
  }
}