package com.mastery.java.task.dto;

import lombok.Getter;

@Getter
public enum Gender {
    MALE ("male"),
    FEMALE ("female");

    private String value;

    Gender(String value) {
        this.value = value;
    }
}
