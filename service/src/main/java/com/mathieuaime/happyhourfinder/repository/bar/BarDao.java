package com.mathieuaime.happyhourfinder.repository.bar;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import java.util.List;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The interface Bar dao.
 */
@RepositoryRestResource
public interface BarDao extends PagingAndSortingRepository<Bar, String> {

  /**
   * Find by near coordinates.
   *
   * @param point the center
   * @param distance the distance
   * @return the list of bars
   */
  List<Bar> findByCoordinatesNear(Point point, Distance distance);
}
