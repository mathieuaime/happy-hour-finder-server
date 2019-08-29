package com.mathieuaime.happyhourfinder.service.bar;

import com.mathieuaime.happyhourfinder.api.bar.BarDto;
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
   * Finds a bar by id.
   *
   * @param id the id
   * @return the optional bar
   */
  Optional<BarDto> findById(Long id);

  /**
   * Saves a bar.
   *
   * @param bar the bar to save
   * @return the bar with the generated id
   */
  BarDto save(BarDto bar);

  /**
   * Deletes by id.
   *
   * @param barId the bar id
   */
  void deleteById(Long barId);
}
