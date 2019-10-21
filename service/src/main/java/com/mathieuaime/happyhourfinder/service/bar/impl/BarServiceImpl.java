package com.mathieuaime.happyhourfinder.service.bar.impl;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import com.mathieuaime.happyhourfinder.repository.bar.BarDao;
import com.mathieuaime.happyhourfinder.service.bar.BarService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class BarServiceImpl implements BarService {

  private final BarDao barDao;

  @Autowired
  BarServiceImpl(BarDao barDao) {
    this.barDao = barDao;
  }

  @Override
  public Page<Bar> findAll(Pageable pageable) {
    return barDao.findAll(pageable);
  }

  @Override
  public Optional<Bar> findByUuid(String uuid) {
    return barDao.findById(uuid);
  }

  @Override
  public List<Bar> findWithin(double lat, double lon, double distance) {
    return barDao.findByCoordinatesNear(new Point(lat, lon), new Distance(distance));
  }

  @Override
  public Bar save(Bar bar) {
    return barDao.save(bar);
  }

  @Override
  public void deleteByUuid(String uuid) {
    barDao.deleteById(uuid);
  }
}
