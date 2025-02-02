package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void update(Resume r);

    Resume get(String fullName);

    void delete(String fullName);

    void clear();

    void save(Resume r);

    List<Resume> getAllSorted();

    int size();
}
