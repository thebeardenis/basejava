package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File>{
    private final File directory;
    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory must not be null.");
        if (directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath()+" is not directory.");
        }
        if (directory.canRead() || directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath()+" is not writable/readable");
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String fullName) {
        return new File(directory, fullName);
    }

    @Override
    protected void doUpdate(Resume r, File searchKey) {
        try {
            doWrite(r, searchKey);
        } catch (IOException e) {
            throw new StorageException("File update error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("Couldn't create new file "+file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(r, file);
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;
    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("File read error" ,file.getName() , e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error" ,file.getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Copy clear storage", directory.getName());
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File f : files) {
            list.add(doGet(f));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    @Override
    public int size() {
        String[] lst = directory.list();
        if (lst == null) {
            throw new StorageException("Directory error", null);
        }
        return lst.length;
    }
}
