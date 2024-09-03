package com.mathieuaime.hhf.bar.persistence;

import com.mathieuaime.hhf.bar.Bar;
import com.mathieuaime.hhf.bar.BarSearch;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BarDao {

    private final BarRowMapper rowMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertIntoBar;

    public BarDao(
            BarRowMapper rowMapper,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertIntoBar = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("bars").usingGeneratedKeyColumns("id");
    }

    public List<Bar> findAll(BarSearch search) {
        Map<String, Object> paramMap = new HashMap<>();

        String sql = """
                SELECT id, name, address, open_time, close_time,
                ST_X(geom::geometry) AS longitude, ST_Y(geom::geometry) AS latitude
                FROM bars WHERE 1=1
                """;

        if (search.name() != null) {
            sql += " AND name = :name";
            paramMap.put("name", search.name());
        }

        if (search.openTime() != null) {
            sql += " AND open_time = :open_time";
            paramMap.put("open_time", search.openTime());
        }

        if (search.closeTime() != null) {
            sql += " AND close_time = :close_time";
            paramMap.put("close_time", search.closeTime());
        }

        if (search.position() != null) {
            sql += " AND ST_DWithin(geom, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :distance)";
            paramMap.put("longitude", search.position().getLongitude());
            paramMap.put("latitude", search.position().getLatitude());
            paramMap.put("distance", search.position().getDistance());
        }

        return namedParameterJdbcTemplate.query(sql, paramMap, rowMapper);
    }

    public Optional<Bar> findById(long id) {
        String sql = """
                SELECT id, name, address, open_time, close_time,
                ST_X(geom::geometry) AS longitude, ST_Y(geom::geometry) AS latitude 
                FROM bars WHERE id = :id
                """;

        var paramMap = Map.of("id", id);

        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, paramMap, rowMapper));
    }

    public Bar create(Bar bar) {
        var parameters = getSaveParameters(bar);
        Number key = insertIntoBar.executeAndReturnKey(parameters);
        return new Bar(key.longValue(), bar.name(), bar.address(), bar.openTime(), bar.closeTime(), bar.position());
    }

    public Bar update(long id, Bar bar) {
        String sql = """
                UPDATE bars SET name = :name, address = :address, open_time = :open_time, close_time = :close_time,
                geom = ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326),
                WHERE id = :id
                """;

        var parameters = getSaveParameters(bar);
        parameters.put("id", id);

        namedParameterJdbcTemplate.update(sql, parameters);

        return bar;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM bars WHERE id = :id";

        var paramMap = Map.of("id", id);

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    private static Map<String, Object> getSaveParameters(Bar bar) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", bar.name());
        parameters.put("address", bar.address());
        parameters.put("open_time", bar.openTime());
        parameters.put("close_time", bar.closeTime());
        parameters.put("longitude", bar.position().longitude());
        parameters.put("latitude", bar.position().latitude());
        return parameters;
    }
}