package com.mastery.java.task.dto;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("male"),
    FEMALE("female");

    private String genderField;

    Gender(String gender) {
        this.genderField = gender;
    }
}