package com.mathieuaime.hhf.persistence.repository;

import com.mathieuaime.hhf.api.model.Bar;
import com.mathieuaime.hhf.persistence.mapper.BarMapper;
import com.mathieuaime.hhf.persistence.model.BarEntity;
import com.mathieuaime.hhf.repository.BarRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaBarRepository implements BarRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Bar> findAll() {
        return em.createQuery("select b from Bar b", BarEntity.class).getResultStream()
                .map(BarMapper::toModel).collect(Collectors.toList());
    }
}
