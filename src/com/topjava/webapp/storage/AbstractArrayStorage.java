package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int ARRAY_LENGTH = 100000;
    protected final Resume[] storage = new Resume[ARRAY_LENGTH];
    protected int size;

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            System.out.println("Resume already exists.");
        } else if (size == ARRAY_LENGTH) {
            System.out.println("Storage is overflow.");
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Don't have " + uuid + " in storage.");
        } else {
            fillDeleteElement(index);
            storage[size-1] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            storage[index] = r;
        } else {
            System.out.println("Don't have " + r.getUuid() + " in storage.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println("Don't have " + uuid + " in storage.");
            return null;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract void fillDeleteElement(int index);
    protected abstract void insertElement(Resume r, int index);
    protected abstract int getIndex(String uuid);
}
