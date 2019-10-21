package com.mathieuaime.happyhourfinder.facade.bar;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The Bar facade interface.
 */
public interface BarFacade {

  /**
   * Finds all pageable bars.
   *
   * @param pageable the pageable request
   * @return the pageable bars
   */
  Page<BarDto> findAll(Pageable pageable);

  /**
   * Finds a bar by uuid.
   *
   * @param uuid the uuid
   * @return the optional bar
   */
  Optional<BarDto> findByUuid(String uuid);

  /**
   * Finds bars within a circle.
   *
   * @param lat the latitude
   * @param lon the longitude
   * @param distance the distance
   * @return the list of bars
   */
  List<BarDto> findWithin(double lat, double lon, double distance);

  /**
   * Saves a bar.
   *
   * @param bar the bar to save
   * @return the bar with the generated id
   */
  BarDto save(BarDto bar);

  /**
   * Deletes by uuid.
   *
   * @param uuid the uuid
   */
  void deleteByUuid(String uuid);
}
