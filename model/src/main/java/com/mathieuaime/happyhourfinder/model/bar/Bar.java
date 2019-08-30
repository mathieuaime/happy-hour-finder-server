package com.mathieuaime.happyhourfinder.model.bar;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "bars")
@Data
@Builder
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class Bar {

  @Id
  @GeneratedValue
  private Long id;

  @NonNull
  private String name;

  @Column(columnDefinition = "POINT")
  private Point coordinates;

  @Embedded
  @AttributeOverrides(value = {
      @AttributeOverride(name = "begin", column = @Column(name = "happy_hour_begin")),
      @AttributeOverride(name = "end", column = @Column(name = "happy_hour_end"))
  })
  private HappyHour happyHour;
}
