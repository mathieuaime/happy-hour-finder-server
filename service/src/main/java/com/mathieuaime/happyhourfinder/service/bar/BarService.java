package com.mathieuaime.happyhourfinder.service.bar;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import java.util.Optional;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The Bar service interface.
 */
public interface BarService {

  /**
   * Finds all pageable bars.
   *
   * @param pageable the pageable request
   * @return the pageable bars
   */
  Page<Bar> findAll(Pageable pageable);

  /**
   * Finds a bar by id.
   *
   * @param id the id
   * @return the optional bar
   */
  Optional<Bar> findById(Long id);

  /**
   * Finds pageable bars by distance.
   *
   * @param pageable the pageable
   * @param center the center
   * @param distance the distance
   * @return the pageable bars
   */
  Page<Bar> findByDistance(Pageable pageable, Point center, long distance);

  /**
   * Saves a bar.
   *
   * @param bar the bar to save
   * @return the bar with the generated id
   */
  Bar save(Bar bar);

  /**
   * Deletes by id.
   *
   * @param barId the bar id
   */
  void deleteById(Long barId);
}
