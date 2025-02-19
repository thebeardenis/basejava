package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serailizer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest{

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}
