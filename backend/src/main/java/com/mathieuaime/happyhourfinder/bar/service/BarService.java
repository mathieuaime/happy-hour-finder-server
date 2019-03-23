package com.mathieuaime.happyhourfinder.bar.service;

import com.mathieuaime.happyhourfinder.bar.model.Bar;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BarService {

  Page<Bar> findAll(Pageable pageable);

  Bar findById(long id);

  Bar save(Bar bar);

  void deleteById(long barId);
}
