package com.mathieuaime.hhf.exception;

import java.util.Map;
import java.util.UUID;

public class ResourceNotFoundException extends HhfException {
    public ResourceNotFoundException(UUID id) {
        super("resource.not_found", Map.of("id", id));
    }
}
