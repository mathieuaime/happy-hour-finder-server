package com.mathieuaime.hhf.happyhour.persistence;

import com.mathieuaime.hhf.happyhour.HappyHour;
import com.mathieuaime.hhf.happyhour.HappyHourSearch;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class HappyHourDao {

    private final HappyHourRowMapper rowMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertIntoHappyHour;

    public HappyHourDao(
            HappyHourRowMapper rowMapper,
            NamedParameterJdbcTemplate namedParameterJdbcTemplate
    ) {
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertIntoHappyHour = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("happy_hours").usingGeneratedKeyColumns("id");
    }

    public List<HappyHour> findAll(HappyHourSearch search) {
        Map<String, Object> paramMap = new HashMap<>();

        String sql = "SELECT id, start_time, end_time, bar_id FROM happy_hours h";

        if (search.position() != null) {
            sql += " INNER JOIN bars b ON h.bar_id = b.id";
        }

        sql += " WHERE 1=1";

        if (search.barId() != null) {
            sql += " AND h.bar_id = :bar_id";
            paramMap.put("bar_id", search.barId());
        }

        if (search.startTime() != null) {
            sql += " AND h.start_time = :start_time";
            paramMap.put("start_time", search.startTime());
        }

        if (search.endTime() != null) {
            sql += " AND h.end_time = :end_time";
            paramMap.put("end_time", search.endTime());
        }

        if (search.position() != null) {
            sql += " AND ST_DWithin(b.geom, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :distance)";
            paramMap.put("longitude", search.position().getLongitude());
            paramMap.put("latitude", search.position().getLatitude());
            paramMap.put("distance", search.position().getDistance());
        }

        return namedParameterJdbcTemplate.query(sql, paramMap, rowMapper);
    }

    public Optional<HappyHour> findById(long id) {
        String sql = "SELECT id, start_time, end_time, bar_id FROM happy_hours WHERE id = :id";

        var paramMap = Map.of("id", id);

        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, paramMap, rowMapper));
    }

    public HappyHour create(HappyHour happyHour) {
        var parameters = getSaveParameters(happyHour);
        Number key = insertIntoHappyHour.executeAndReturnKey(parameters);
        return new HappyHour(key.longValue(), happyHour.startTime(), happyHour.endTime(), happyHour.barId());
    }

    public HappyHour update(long id, HappyHour happyHour) {
        String sql = """
                UPDATE happy_hours SET start_time = :start_time, end_time = :end_time, bar_id = :bar_id
                WHERE id = :id
                """;

        var parameters = getSaveParameters(happyHour);
        parameters.put("id", id);

        namedParameterJdbcTemplate.update(sql, parameters);

        return happyHour;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM happy_hours WHERE id = :id";

        var paramMap = Map.of("id", id);

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    private static Map<String, Object> getSaveParameters(HappyHour happyHour) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("start_time", happyHour.startTime());
        parameters.put("end_time", happyHour.endTime());
        parameters.put("bar_id", happyHour.barId());
        return parameters;
    }
}