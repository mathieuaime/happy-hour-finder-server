package com.mathieuaime.hhf.happyhour.persistence;

import com.mathieuaime.hhf.happyhour.HappyHour;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HappyHourRowMapper implements RowMapper<HappyHour> {
    @Override
    public HappyHour mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new HappyHour(
                rs.getLong("id"),
                rs.getTime("startTime").toLocalTime(),
                rs.getTime("endTime").toLocalTime(),
                rs.getLong("bar_id")
        );
    }
}