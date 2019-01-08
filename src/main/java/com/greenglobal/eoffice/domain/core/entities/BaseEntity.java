package com.greenglobal.eoffice.domain.core.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private int id;
  
  @Column(name = "createdAt", nullable = false)
  private Date createdAt;
  
  @Column(name = "modifiedAt", nullable = true)
  private Date modifiedAt;
  
  @Column(name = "isDeleted", nullable = false)
  private boolean isDeleted = false;
  
  @Column(name = "deletedAt", nullable = true)
  private Date deletedAt;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }
  
  /**
   * @return the deletedAt
   */
  public Date getDeletedAt() {
    return deletedAt;
  }

  /**
   * @param deletedAt the deletedAt to set
   */
  public void setDeletedAt(Date deletedAt) {
    this.deletedAt = deletedAt;
  }

  /**
   * @return the isDeleted
   */
  public boolean getIsDeleted() {
    return isDeleted;
  }

  /**
   * @param isDeleted the isDeleted to set
   */
  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  /**
   * @return the modifiedAt
   */
  public Date getModifiedAt() {
    return modifiedAt;
  }

  /**
   * @param modifiedAt the modifiedAt to set
   */
  public void setModifiedAt(Date modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  /**
   * @return the createdAt
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}