package com.mathieuaime.happyhourfinder;

import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import com.mathieuaime.happyhourfinder.repository.bar.BarDao;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HappyHourFinderApplication.class)
public class HappyHourFinderIntegrationTests {

  private static final GeometryFactory geometryFactory =
      new GeometryFactory(new PrecisionModel(), 26910);

  @Autowired
  private BarDao barDao;

  private static final HappyHour HAPPY_HOUR = HappyHour
      .create(LocalTime.of(12, 0), Duration.ofHours(1));

  private static final Point POINT = geometryFactory.createPoint(new Coordinate(1, 2));

  @Before
  public void setUp() throws Exception {
    Bar bar = Bar.create(1L, "Bar1", POINT, HAPPY_HOUR);
    barDao.save(bar);
  }

  @Test
  public void getBar() throws Exception {
    Optional<Bar> bar = barDao.findById(1L);
    assertThat(bar).map(Bar::getName).contains("Bar1");
  }
}
