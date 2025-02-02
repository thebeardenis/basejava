package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeleteElement(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    protected Integer getSearchKey(String fullName) {
        for (int index = 0; index < size; index++) {
            if (fullName.equals(storage[index].getFullName())) {
                return index;
            }
        }
        return -1;
    }

}
