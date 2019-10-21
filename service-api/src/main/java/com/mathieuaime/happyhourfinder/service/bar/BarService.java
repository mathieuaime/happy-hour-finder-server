package com.mathieuaime.happyhourfinder.service.bar;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import java.util.List;
import java.util.Optional;
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
   * Finds a bar by uuid.
   *
   * @param uuid the uuid
   * @return the optional bar
   */
  Optional<Bar> findByUuid(String uuid);

  /**
   * Finds pageable bars by distance.
   *
   * @param lat the latitude
   * @param lon the longitude
   * @param distance the distance
   * @return the pageable bars
   */
  List<Bar> findWithin(double lat, double lon, double distance);

  /**
   * Saves a bar.
   *
   * @param bar the bar to save
   * @return the bar with the generated id
   */
  Bar save(Bar bar);

  /**
   * Deletes by uuid.
   *
   * @param uuid the uuid
   */
  void deleteByUuid(String uuid);
}
