package com.mathieuaime.hhf.bar.persistence;

import com.mathieuaime.hhf.bar.Bar;
import com.mathieuaime.hhf.geo.Coordinates;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BarRowMapper implements RowMapper<Bar> {
    @Override
    public Bar mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Bar(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getTime("open_time").toLocalTime(),
                rs.getTime("close_time").toLocalTime(),
                new Coordinates(rs.getDouble("latitude"), rs.getDouble("longitude"))
        );
    }
}