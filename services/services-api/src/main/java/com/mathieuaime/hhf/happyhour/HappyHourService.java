package com.mathieuaime.hhf.happyhour;

import java.util.List;

public interface HappyHourService {
    List<HappyHour> findAll(HappyHourSearch search);

    HappyHour findById(long id);

    HappyHour create(HappyHour happyHour);

    HappyHour update(long id, HappyHour happyHour);

    void deleteById(long id);
}
