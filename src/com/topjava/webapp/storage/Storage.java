package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public interface Storage {

    void update(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    void clear();

    void save(Resume r);

    Resume[] getAll();

    int size();
}
