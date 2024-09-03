package com.mathieuaime.hhf.exception;

import java.util.Collections;

public class BadRequestException extends HhfException {
    public BadRequestException() {
        super("resource.bad_request", Collections.emptyMap());
    }
}
