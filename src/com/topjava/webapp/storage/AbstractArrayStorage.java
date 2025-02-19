package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    private static final Logger LOG2 = Logger.getLogger(AbstractArrayStorage.class.getName());

    @Override
    public void doSave(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            LOG2.warning("Overflow storage in doSave " + r);
            throw new StorageException("Overflow storage", r.getUuid());
        } else {
            insertElement(r, index);
            size++;
        }
    }

    @Override
    public void doDelete(Integer index) {
        fillDeleteElement(index);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    @Override
    public void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    public Resume doGet(Integer index) {
        return storage[index];
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
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void fillDeleteElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract Integer getSearchKey(String uuid);
}
