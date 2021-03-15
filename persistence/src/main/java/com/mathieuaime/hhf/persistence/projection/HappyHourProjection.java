package com.mathieuaime.hhf.persistence.projection;

import java.time.LocalTime;
import java.util.UUID;

public class HappyHourProjection {
    private final UUID id;
    private final LocalTime start;
    private final LocalTime end;
    private final UUID barId;
    private final String barName;
    private final LocalTime barOpen;
    private final LocalTime barClose;

    public HappyHourProjection(
            UUID id,
            LocalTime start,
            LocalTime end,
            UUID barId,
            String barName,
            LocalTime barOpen,
            LocalTime barClose
    ) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.barId = barId;
        this.barName = barName;
        this.barOpen = barOpen;
        this.barClose = barClose;
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

    public UUID getBarId() {
        return barId;
    }

    public String getBarName() {
        return barName;
    }

    public LocalTime getBarOpen() {
        return barOpen;
    }

    public LocalTime getBarClose() {
        return barClose;
    }
}
