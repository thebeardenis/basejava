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
import java.util.UUID;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String FULL_NAME_1 = "Aleksei Popov";
    private static final Resume R1 = new Resume(UUID_1,FULL_NAME_1);

    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String FULL_NAME_2 = "Denis Smirnov";
    private static final Resume R2 = new Resume(UUID_2, FULL_NAME_2);

    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String FULL_NAME_3 = "Sergei Fedorov";
    private static final Resume R3 = new Resume(UUID_3, FULL_NAME_3);

    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String FULL_NAME_4 = "Kiril Ponomarev";
    private static final Resume R4 = new Resume(UUID_4, FULL_NAME_4);

    static {
        R1.addContact(ContactsType.MAIL, "denis_2000_kolesnik");
        R1.addContact(ContactsType.PHONE, "+79662108956");
        R4.addContact(ContactsType.MAIL, "best_main_in_world");
        R4.addContact(ContactsType.GITHUB, "github.thebeardenis.com");
/*        List<AboutOrganization.Position> POS1 = new ArrayList<>();
        POS1.add(new AboutOrganization.Position(2005, Month.DECEMBER, 2010, Month.MAY, "position backend gamedev", "create game solo"));
        POS1.add(new AboutOrganization.Position(2001, Month.JANUARY, 2002, Month.DECEMBER, "position2", "content2"));
        List<AboutOrganization.Position> POS2 = new ArrayList<>();
        POS2.add(new AboutOrganization.Position(2014, Month.DECEMBER, 2010, Month.MAY,"Primary school", "PapaRimski"));
        POS2.add(new AboutOrganization.Position(2018, Month.SEPTEMBER, 2025, Month.MAY, "High school", "big student"));
        List<AboutOrganization.Position> POS3 = new ArrayList<>();
        POS3.add(new AboutOrganization.Position(2005, Month.DECEMBER, "All time development", "to now time"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal section 1"));
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective 1"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSections("Achievement is big tech skills dev", "school boy"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSections("Winner big olympics", "Have gold medal with school"));
        R1.addSection(SectionType.EXPERIENCE,
                new AboutOrganizationSection(
                        new AboutOrganization("Organization 1", "https://thebeardenis.git.io", POS1)));
        R1.addSection(SectionType.EDUCATION,
                new AboutOrganizationSection(
                        new AboutOrganization("School number 15",  "https://school15.ru", POS2)));
        R2.addSection(SectionType.EXPERIENCE,
                new AboutOrganizationSection(
                        new AboutOrganization("Organization 14","https://youtube.com", POS3)));
 */
    }

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