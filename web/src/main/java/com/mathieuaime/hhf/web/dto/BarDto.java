package com.mathieuaime.hhf.web.dto;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BarDto {
    private UUID id;
    private String name;
    private LocalTime open;
    private LocalTime close;
    private List<HappyHourDto> happyHours;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getOpen() {
        return open;
    }

    public void setOpen(LocalTime open) {
        this.open = open;
    }

    public LocalTime getClose() {
        return close;
    }

    public void setClose(LocalTime close) {
        this.close = close;
    }

    public List<HappyHourDto> getHappyHours() {
        return happyHours;
    }

    public void setHappyHours(List<HappyHourDto> happyHours) {
        this.happyHours = happyHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarDto barDto = (BarDto) o;
        return Objects.equals(id, barDto.id) && Objects.equals(name, barDto.name) && Objects.equals(open, barDto.open) && Objects.equals(close, barDto.close) && Objects.equals(happyHours, barDto.happyHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, open, close, happyHours);
    }

    @Override
    public String toString() {
        return "BarDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", happyHours=" + happyHours +
                '}';
    }
}
