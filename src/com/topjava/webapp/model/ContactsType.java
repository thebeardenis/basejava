package com.topjava.webapp.model;

public enum ContactsType {
    PHONE("Your phone number."),
    HOME_PHONE("Your home phone."),
    SKYPE("Your skype."),
    MAIL("Your mail address."),
    GITHUB("Your github acc."),
    STACKOVERFLOW("Your acc in stackoverflow."),
    HOME_PAGE("Your home page.");

    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public  String getTitle() {
        return title;
    }
}
