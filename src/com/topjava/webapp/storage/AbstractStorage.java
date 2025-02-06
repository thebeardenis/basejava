package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getSearchKey(String fullName);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) {
        SK searchKey = getExistedSearchKey(r.getFullName());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
//        LOG.info("Save " + r);
        SK searchKey = getNotExistedSearchKey(r.getFullName());
        doSave(r, searchKey);
    }

    public void delete(String fullName) {
//        LOG.info("Delete " + fullName);
        SK searchKey = getExistedSearchKey(fullName);
        doDelete(searchKey);
    }

    public Resume get(String fullName) {
//        LOG.info("Get " + fullName);
        SK searchKey = getExistedSearchKey(fullName);
        return (Resume) doGet(searchKey);
    }

    private SK getExistedSearchKey(String fullName) {
        SK searchKey = getSearchKey(fullName);
        if (!isExist(searchKey)) {
            LOG.warning("Get key not exist " + fullName);
            throw new NotExistStorageException(fullName);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String fullName) {
        SK searchKey = getSearchKey(fullName);
        if (isExist(searchKey)) {
            LOG.warning("Get key already " + fullName);
            throw new ExistStorageException(fullName);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
//        LOG.info("Get All Sorted");
        List<Resume> list = doCopyAll();
        list.sort(Comparator.comparing(Resume::getFullName));
        return list;
    }
}
