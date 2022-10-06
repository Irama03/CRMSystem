package com.models;

public enum StateType {
    NEW,
    IN_PROGRESS,
    DONE,
    FAILED;

    public boolean isFinished() {
        return this == DONE || this == FAILED;
    }
}
