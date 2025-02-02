package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String fullName);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getFullName());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getFullName());
        doSave(r, searchKey);
    }

    public void delete(String fullName) {
        Object searchKey = getExistedSearchKey(fullName);
        doDelete(searchKey);
    }

    public Resume get(String fullName) {
        Object searchKey = getExistedSearchKey(fullName);
        return (Resume) doGet(searchKey);
    }

    private Object getExistedSearchKey(String fullName) {
        Object searchKey = getSearchKey(fullName);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(fullName);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String fullName) {
        Object searchKey = getSearchKey(fullName);
        if (isExist(searchKey)) {
            throw new ExistStorageException(fullName);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list, Comparator.comparing(Resume::getFullName));
        return list;
    }
}
