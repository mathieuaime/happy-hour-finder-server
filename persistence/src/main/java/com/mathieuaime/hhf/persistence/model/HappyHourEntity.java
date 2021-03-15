package com.mathieuaime.hhf.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalTime;
import java.util.UUID;

@Entity(name = "HappyHour")
public class HappyHourEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalTime start;

    private LocalTime end;

    @ManyToOne
    private BarEntity bar;

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

    public BarEntity getBar() {
        return bar;
    }

    public void setBar(BarEntity bar) {
        this.bar = bar;
    }
}
