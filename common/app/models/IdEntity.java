package models;

import javax.persistence.*;

/**
 * <p>Project: fsp</p>
 * <p>Title: IdEntity.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@MappedSuperclass
public abstract class IdEntity implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SRC")
  @SequenceGenerator(name = "SRC", sequenceName = "SRC", allocationSize = 20)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}