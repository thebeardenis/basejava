package com.topjava.webapp.exception;

public class ExistStorageException extends StorageException{

    public ExistStorageException(String uuid) {
        super("Resume already exist", uuid);
    }
}
