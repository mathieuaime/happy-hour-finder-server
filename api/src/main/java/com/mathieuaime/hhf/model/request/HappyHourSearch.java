package com.mathieuaime.hhf.model.request;

import java.util.UUID;

public class HappyHourSearch {
    private static final HappyHourSearch ALL = new HappyHourSearch();

    private UUID barId;

    public static HappyHourSearch all() {
        return ALL;
    }

    public UUID getBarId() {
        return barId;
    }

    public void setBarId(UUID barId) {
        this.barId = barId;
    }
}
