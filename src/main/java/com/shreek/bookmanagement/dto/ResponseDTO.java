package com.shreek.bookmanagement.dto;

import java.time.LocalDateTime;

public class ResponseDTO {
    private String message;
    private int statusCode;
    private Object response;

    private LocalDateTime timestamp = LocalDateTime.now();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
