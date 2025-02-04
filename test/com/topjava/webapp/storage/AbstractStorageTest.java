package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME_1 = "Aleksei Popov";
    private static final Resume RESUME_1 = new Resume(UUID_1,FULL_NAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String FULL_NAME_2 = "Denis Smirnov";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME_3 = "Sergei Fedorov";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

    private static final String UUID_4 = "uuid4";
    private static final String FULL_NAME_4 = "Kiril Ponomarev";
    private static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(FULL_NAME_1);
        assertSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void update() throws Exception {
        storage.update(new Resume(UUID_3, FULL_NAME_3));
        assertGet(RESUME_3);
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> second = storage.getAllSorted();
        Assert.assertEquals(RESUME_1, second.get(0));
        Assert.assertEquals(RESUME_2, second.get(1));
        Assert.assertEquals(RESUME_3, second.get(2));
        assertSize(3);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getFullName()));
    }

}