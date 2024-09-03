package com.mathieuaime.hhf.exception;

import java.util.Map;

public abstract class HhfException extends RuntimeException {
    private final String code;
    private final Map<String, Object> params;

    public HhfException(String code, Map<String, Object> params) {
        this.code = code;
        this.params = params;
    }

    public String getCode() {
        return code;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
