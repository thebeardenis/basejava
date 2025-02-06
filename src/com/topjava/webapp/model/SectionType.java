package com.topjava.webapp.model;

public enum SectionType {
    PERSONAL("Personal qualities."),
    OBJECTIVE("Position."),
    ACHIEVEMENT("Achievement."),
    QUALIFICATIONS("Qualification."),
    EXPERIENCE("Experience."),
    EDUCATION("Education.");

    private final String title;

    SectionType(String title) {
        this.title = title;
    }

    public  String getTitle() {
        return title;
    }
}
