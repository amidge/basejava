package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("D:\\Projects\\topjava.ru\\basejava\\storage");
    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    public static final Resume R1;

    protected static final String UUID_2 = "uuid2";
    public static final Resume R2;

    protected static final String UUID_3 = "uuid3";
    public static final Resume R3;

    protected static final String UUID_4 = "uuid4";
    public static final Resume R4;

    static {
        R1 = ResumeTestData.generateResume(UUID_1, "Name1");
        R2 = ResumeTestData.generateResume(UUID_2, "Name2");
        R3 = ResumeTestData.generateResume(UUID_3, "Name3");
        R4 = ResumeTestData.generateResume(UUID_4, "Name4");
    }
//    static {
//        R1 = new Resume(UUID_1, "Name1");
//        R2 = new Resume(UUID_2, "Name2");
//        R3 = new Resume(UUID_3, "Name3");
//        R4 = new Resume(UUID_4, "Name4");
//

//        R2.addContact(ContactType.SKYPE, "skype2");
//        R2.addContact(ContactType.PHONE, "22222");
//        R2.addSection(SectionType.EXPERIENCE,
//                new OrganizationSection(
//                        new Organization("Organization2", "http://Organization2.ru",
//                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
//    }



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
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAlSorted();
        assertEquals(3, list.size());
        assertEquals(Arrays.asList(R1, R2, R3), list);
    }

    @Test
    public void save() {
        storage.save(R4);
        assertEquals(4, storage.size());
        assertEquals(R4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void get() {
        assertEquals(R1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}