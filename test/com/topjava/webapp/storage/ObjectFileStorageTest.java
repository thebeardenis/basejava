package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serailizer.ObjectStreamSerializer;

public class ObjectFileStorageTest extends AbstractStorageTest{
    public ObjectFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
