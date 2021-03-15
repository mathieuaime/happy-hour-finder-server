package com.mathieuaime.hhf.persistence.jpa;

import com.mathieuaime.hhf.exception.ResourceNotFoundException;
import com.mathieuaime.hhf.model.Bar;
import com.mathieuaime.hhf.model.request.BarSearch;
import com.mathieuaime.hhf.persistence.mapper.BarMapper;
import com.mathieuaime.hhf.persistence.entity.BarEntity;
import com.mathieuaime.hhf.persistence.projection.HappyHourProjection;
import com.mathieuaime.hhf.repository.BarRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaBarRepository implements BarRepository {
    private static final String FIND_BY_ID_QUERY = """
            select new com.mathieuaime.hhf.persistence.projection.HappyHourProjection(
                hh.id, hh.start, hh.end, hh.bar.id, hh.bar.name, hh.bar.open, hh.bar.close
            ) 
            from HappyHour hh 
            where hh.bar.id = ?1
            """;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Bar> findAll(BarSearch search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        var query = cb.createQuery(BarEntity.class);
        var r = query.from(BarEntity.class);
        Predicate predicate = addPredicates(cb, cb.and(), r, search);
        query = query.where(predicate);
        return em.createQuery(query).getResultStream().map(BarMapper::toModel).collect(Collectors.toList());
    }

    private Predicate addPredicates(CriteriaBuilder cb, Predicate predicate, Root<BarEntity> r, BarSearch search) {
        return predicate;
    }

    @Override
    public Bar findById(UUID id) {
        List<HappyHourProjection> projections = em.createQuery(FIND_BY_ID_QUERY, HappyHourProjection.class)
                .setParameter(1, id)
                .getResultList();
        return BarMapper.toModel(projections).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Bar save(Bar bar) {
        return em.merge(bar);
    }

    @Override
    public void deleteById(UUID id) {
        Bar bar = findById(id);
        em.remove(bar);
    }
}
