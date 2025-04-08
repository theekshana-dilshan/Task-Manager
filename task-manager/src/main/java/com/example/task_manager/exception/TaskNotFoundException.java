package com.example.task_manager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
