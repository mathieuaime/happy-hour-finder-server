package com.mathieuaime.happyhourfinder.bar.service.impl;

import com.mathieuaime.happyhourfinder.bar.dao.BarDao;
import com.mathieuaime.happyhourfinder.bar.exception.BarNotFoundException;
import com.mathieuaime.happyhourfinder.bar.model.Bar;
import com.mathieuaime.happyhourfinder.bar.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
  public Bar findById(long id) {
    return barDao.findById(id).orElseThrow(() -> new BarNotFoundException(id));
  }

  @Override
  public Bar save(Bar bar) {
    return barDao.save(bar);
  }

  @Override
  public void deleteById(long id) {
    try {
      barDao.deleteById(id);
    } catch (DataAccessException e) {
      throw new BarNotFoundException(id);
    }
  }
}
