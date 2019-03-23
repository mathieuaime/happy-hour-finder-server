package com.mathieuaime.happyhourfinder.bar.exception;

import com.mathieuaime.happyhourfinder.common.error.NotFoundException;

public class BarNotFoundException extends NotFoundException {

  public BarNotFoundException(Long barId) {
    super("Bar " + barId + " not found");
  }
}
