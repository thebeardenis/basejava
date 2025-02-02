package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int ARRAY_LENGTH = 10000;
    protected Resume[] storage = new Resume[ARRAY_LENGTH];
    protected int size;

    @Override
    public void doSave(Resume r, Object index) {
        if (size == ARRAY_LENGTH) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, (Integer) index);
            size++;
        }
    }

    @Override
    public void doDelete(Object index) {
        fillDeleteElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    @Override
    public void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract void fillDeleteElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract Integer getSearchKey(String fullName);
}
