package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeleteElement(int index) {
        int countEl = size - index - 1;
        if (countEl > 0) {
            System.arraycopy(storage, index + 1, storage, index, countEl);
        }
    }

    @Override
    protected void insertElement(Resume r, int index) {
        int binaryIndex = -index - 1;
        System.arraycopy(storage, binaryIndex, storage, binaryIndex + 1, size - binaryIndex);
        storage[binaryIndex] = r;
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
