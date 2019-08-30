package com.mathieuaime.happyhourfinder.repository.bar;

import com.mathieuaime.happyhourfinder.model.bar.Bar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BarDao extends PagingAndSortingRepository<Bar, Long> {

}
