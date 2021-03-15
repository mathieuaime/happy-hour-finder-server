package com.mathieuaime.hhf.persistence.jpa;

import com.mathieuaime.hhf.exception.ResourceNotFoundException;
import com.mathieuaime.hhf.model.HappyHour;
import com.mathieuaime.hhf.model.request.HappyHourSearch;
import com.mathieuaime.hhf.persistence.mapper.HappyHourMapper;
import com.mathieuaime.hhf.persistence.entity.HappyHourEntity;
import com.mathieuaime.hhf.repository.HappyHourRepository;
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
public class JpaHappyHourRepository implements HappyHourRepository {
    private static final String FIND_BY_ID_QUERY = "select hh from HappyHour hh where hh.id = ?1";

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<HappyHour> findAll(HappyHourSearch search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        var query = cb.createQuery(HappyHourEntity.class);
        var r = query.from(HappyHourEntity.class);
        Predicate predicate = computePredicates(cb, cb.and(), r, search);
        query = query.where(predicate);
        return em.createQuery(query).getResultStream().map(HappyHourMapper::toModel).collect(Collectors.toList());
    }

    private Predicate computePredicates(CriteriaBuilder cb, Predicate predicate, Root<HappyHourEntity> r, HappyHourSearch search) {
        if (search.getBarId() != null) {
            predicate = cb.and(predicate, cb.equal(r.get("bar_id"), search.getBarId()));
        }

        return predicate;
    }

    public HappyHour findById(UUID id) {
        return em.createQuery(FIND_BY_ID_QUERY, HappyHourEntity.class)
                .setParameter(1, id)
                .getResultStream()
                .findFirst()
                .map(HappyHourMapper::toModel)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public HappyHour save(HappyHour happyHour) {
        return em.merge(happyHour);
    }

    @Override
    public void deleteById(UUID id) {
        HappyHour happyHour = findById(id);
        em.remove(happyHour);
    }
}
