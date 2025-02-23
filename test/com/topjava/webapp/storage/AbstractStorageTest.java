package com.topjava.webapp.storage;

import com.topjava.webapp.Config;
import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.ContactsType;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static com.topjava.webapp.TestData.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;


    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
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
        Resume resume = new Resume(UUID_1, "New name");
        R1.addContact(ContactsType.MAIL, "mail@google.com");
        R1.addContact(ContactsType.MAIL, "new_mail");
        R1.addContact(ContactsType.GITHUB, "new_git_hub");
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_1));
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("dummy"));
    }

    @Test
    public void get() throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
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
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
        assertSize(3);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

}