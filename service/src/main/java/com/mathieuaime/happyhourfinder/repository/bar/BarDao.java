package com.mathieuaime.happyhourfinder.repository.bar;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import java.util.List;
import java.util.Optional;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BarDao extends PagingAndSortingRepository<Bar, Long> {

  List<Bar> findByCoordinatesNear(Point point, Distance distance);

  Optional<Bar> findByName(String name);
}
