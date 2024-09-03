package com.mathieuaime.hhf.exception;

import java.util.Map;

public class ResourceNotFoundException extends HhfException {
    public ResourceNotFoundException(long id) {
        super("resource.not_found", Map.of("id", id));
    }
}
