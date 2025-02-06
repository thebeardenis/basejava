package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public class ResumeTestDate{
    private final Resume resume;

    public ResumeTestDate(Resume resume, String uuid, String fullName) {
        this.resume = new Resume(uuid, fullName);
    }
}
