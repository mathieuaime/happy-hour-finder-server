package com.mathieuaime.happyhourfinder.comparator;

import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.model.bar.HappyHour;
import java.time.LocalTime;
import java.util.Comparator;
import org.junit.Test;

public class BarComparatorTest {

  private static final HappyHour HAPPY_HOUR_1 = new HappyHour().begin(LocalTime.of(12, 0));
  private static final HappyHour HAPPY_HOUR_2 = new HappyHour().begin(LocalTime.of(14, 0));
  private static final Bar BAR_1 = new Bar().uuid("1234").name("Bar1").happyHour(HAPPY_HOUR_1);
  private static final Bar BAR_2 = new Bar().uuid("12345").name("Bar2").happyHour(HAPPY_HOUR_2);

  @Test
  public void compareSameBar() {
    Comparator<Bar> comparator = BarComparator.compareByHappyHour(LocalTime.of(10, 0));

    assertThat(comparator.compare(BAR_1, BAR_1)).isEqualTo(0);
  }

  @Test
  public void compareBarBeforeNow() {
    Comparator<Bar> comparator = BarComparator.compareByHappyHour(LocalTime.of(10, 0));

    assertThat(comparator.compare(BAR_1, BAR_2)).isEqualTo(-1);
    assertThat(comparator.compare(BAR_2, BAR_1)).isEqualTo(1);
  }

  @Test
  public void compareBarAfterNow() {
    Comparator<Bar> comparator = BarComparator.compareByHappyHour(LocalTime.of(13, 0));

    assertThat(comparator.compare(BAR_1, BAR_2)).isEqualTo(1);
    assertThat(comparator.compare(BAR_2, BAR_1)).isEqualTo(-1);
  }
}