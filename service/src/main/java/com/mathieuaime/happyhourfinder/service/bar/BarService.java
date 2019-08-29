package com.mathieuaime.happyhourfinder.service.bar;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
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
   * Finds a bar by id.
   *
   * @param id the id
   * @return the optional bar
   */
  Optional<Bar> findById(Long id);

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
