package com.mathieuaime.happyhourfinder.bar.dao;

import com.mathieuaime.happyhourfinder.bar.model.Bar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BarDAO extends PagingAndSortingRepository<Bar, Long> {
}
