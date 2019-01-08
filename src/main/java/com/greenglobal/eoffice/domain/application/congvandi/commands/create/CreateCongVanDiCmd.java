package com.greenglobal.eoffice.domain.application.congvandi.commands.create;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.greenglobal.eoffice.domain.core.entities.ItemCommand;

public class CreateCongVanDiCmd extends ItemCommand {
  private String soCongVan;
  private Date ngayCongVan;
  private String noiDung;
  private String trichYeu;
  private int[] dsDonViNhan;

  /**
   * @return the trichYeu
   */
  public String getTrichYeu() {
    return trichYeu;
  }

  /**
   * @return the noiDung
   */
  public String getNoiDung() {
    return noiDung;
  }

  public String getSoCongVan() {
    return this.soCongVan;
  }

  /**
   * @return the ngayCongVan
   */
  public Date getNgayCongVan() {
    return this.ngayCongVan;
  }

  /**
   * @return the dsDonViNhan
   */
  public int[] getDsDonViNhan() {
    return this.dsDonViNhan;
  }
}