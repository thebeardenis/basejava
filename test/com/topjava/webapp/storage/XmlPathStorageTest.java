package com.topjava.webapp.storage;

import com.topjava.webapp.storage.serailizer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest{

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
