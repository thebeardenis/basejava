package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            while (AbstractArrayStorage.ARRAY_LENGTH != storage.size()) {
                storage.save(new Resume("something"));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("something"));
    }
}
