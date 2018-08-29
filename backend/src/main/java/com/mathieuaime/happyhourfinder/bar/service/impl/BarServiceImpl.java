package com.mathieuaime.happyhourfinder.bar.service.impl;

import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class BarServiceImpl implements BarService {

  private final BarDao barDao;

  @Autowired
  public BarServiceImpl(BarDao barDao) {
    this.barDao = barDao;
  }

  @Override
  public Page<Bar> findAll(Pageable pageable) {
    return barDao.findAll(pageable);
  }

  @Override
  public Optional<Bar> findById(Long id) {
    return barDao.findById(id);
  }

  @Override
  public Bar save(Bar bar) {
    return barDao.save(bar);
  }

  @Override
  public void deleteById(Long barId) {
    barDao.deleteById(barId);
  }
}
