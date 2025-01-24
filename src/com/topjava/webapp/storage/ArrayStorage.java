package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private final int ARRAY_LENGTH = 10000;
    private final Resume[] storage = new Resume[ARRAY_LENGTH];
    private int size;

    private int getIndex(String uuid) {
        for (int index = 0; index < size; index++) {
            if (uuid.equals(storage[index].getUuid())) {
                return index;
            }
        }
        return -1;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Don't have " + r.getUuid() + " in storage.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Don't have " + uuid + " in storage.");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size-1];
            storage[size-1] = null;
            size--;
        } else {
            System.out.println("Don't have " + uuid + " in storage.");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) == -1) {
            if (size < ARRAY_LENGTH) {
                storage[size++] = r;
            }else {
                System.out.println("Storage is overflow.");
            }
        } else {
            System.out.println("Resume "+r.getUuid()+" already in storage.");
        }
    }

    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
