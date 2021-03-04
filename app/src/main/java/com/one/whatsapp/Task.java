package com.one.whatsapp;

public class Task {
    private String number;
    private String message;
    private String time_of_execution;
    private String status;

    public Task(String number, String message, String time_of_execution, String status) {
        this.number = number;
        this.message = message;
        this.time_of_execution = time_of_execution;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime_of_execution() {
        return time_of_execution;
    }

    public void setTime_of_execution(String time_of_execution) {
        this.time_of_execution = time_of_execution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Task() {
    }
}
