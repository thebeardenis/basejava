package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serailizer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest{

    public ObjectPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}
