package com.gits.health.HealthApp.exception;

public class GitsException extends Exception{
    private Integer errorCode;
    private String message;
    private String[] values;

    public GitsException(String message) {
        super(message);
        this.errorCode = 500;
        this.message = message;
    }

    public GitsException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public GitsException(Integer errorCode, String message, String... values) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.values = values;
    }

    public GitsException(Exception e) {
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String[] getValues() {
        return this.values;
    }
}
