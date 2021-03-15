package com.mathieuaime.hhf.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.UUID;

@Entity(name = "Bar")
public class BarEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private LocalTime open;

    private LocalTime close;

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

    public void setOpen(LocalTime start) {
        this.open = start;
    }

    public LocalTime getClose() {
        return close;
    }

    public void setClose(LocalTime close) {
        this.close = close;
    }
}
