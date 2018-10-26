package com.mathieuaime.happyhourfinder.bar.model;

import com.vividsolutions.jts.geom.Point;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Builder
public class Bar {

  @Id
  @GeneratedValue
  private Long id;

  @NonNull
  private String name;

  private Point coordinates;
}
