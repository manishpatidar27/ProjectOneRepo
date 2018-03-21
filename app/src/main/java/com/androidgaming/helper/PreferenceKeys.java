package com.androidgaming.helper;

/**
 * Created by Manish Patidar on 10-08-2017.
 */

public enum PreferenceKeys {

    DUMMY("DUMMY"),

    USER_ID("USER_ID"),

    USER_NAME("USER_NAME"),

    USER_EMAIL("USER_EMAIL"),

    USER_USERNAME("USER_USERNAME"),

    USER_TYPE("USER_TYPE"),

    USER_LOCATION("USER_TYPE"),

    API_TOKEN("API_TOKEN"),

    USER_IS_ACTIVE("USER_IS_ACTIVE");



    private String text;

    private PreferenceKeys(final String text) {
        this.text = text;
    }

    public String getKey() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    }
