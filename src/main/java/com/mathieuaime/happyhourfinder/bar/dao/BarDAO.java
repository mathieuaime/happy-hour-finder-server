package com.mathieuaime.happyhourfinder.bar.dao;

import com.mathieuaime.happyhourfinder.bar.model.Bar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface BarDAO extends PagingAndSortingRepository<Bar, Long> {
}
