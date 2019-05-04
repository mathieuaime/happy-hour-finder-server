package com.mathieuaime.happyhourfinder.bar.model;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;

@Data
public class Trip {

  private final List<Bar> bars = new LinkedList<>();

  public void addBar(Bar bar) {
    bars.add(bar);
  }

  public List<Bar> getBars() {
    return new LinkedList<>(bars);
  }

  public int getCount() {
    return bars.size();
  }
}
