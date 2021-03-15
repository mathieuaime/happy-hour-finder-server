package com.mathieuaime.hhf.api.model;

import java.time.LocalTime;
import java.util.UUID;

public class HappyHour {
    private final UUID id;
    private final LocalTime start;
    private final LocalTime end;

    public HappyHour(UUID id, LocalTime start, LocalTime end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public UUID getId() {
        return id;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "HappyHour{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
