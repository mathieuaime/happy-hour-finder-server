package com.mathieuaime.hhf.web.dto;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class HappyHourDto {
    private UUID id;
    private LocalTime start;
    private LocalTime end;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HappyHourDto that = (HappyHourDto) o;
        return Objects.equals(id, that.id) && Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end);
    }

    @Override
    public String toString() {
        return "HappyHourDto{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
