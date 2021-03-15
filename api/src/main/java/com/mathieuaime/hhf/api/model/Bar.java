package com.mathieuaime.hhf.api.model;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Bar {
    private final UUID id;
    private final String name;
    private final LocalTime open;
    private final LocalTime close;
    private final List<HappyHour> happyHours;

    public Bar(UUID id, String name, LocalTime open, LocalTime close) {
        this(id, name, open, close, Collections.emptyList());
    }

    public Bar(UUID id, String name, LocalTime open, LocalTime close, List<HappyHour> happyHours) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.close = close;
        this.happyHours = List.copyOf(happyHours);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalTime getOpen() {
        return open;
    }

    public LocalTime getClose() {
        return close;
    }

    public List<HappyHour> getHappyHours() {
        return happyHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bar bar = (Bar) o;
        return Objects.equals(id, bar.id) && Objects.equals(name, bar.name) && Objects.equals(open, bar.open) && Objects.equals(close, bar.close) && Objects.equals(happyHours, bar.happyHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, open, close, happyHours);
    }

    @Override
    public String toString() {
        return "Bar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", happyHours=" + happyHours +
                '}';
    }
}
