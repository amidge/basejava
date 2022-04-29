package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
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
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactType.MAIL,"mail11@reg.ru");
        R1.addContact(ContactType.PHONE,"+79103426627");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new TextSection("Achievement1"));
        R1.addSection(SectionType.QUALIFICATION, new TextSection("Qualification1"));
        R1.addSection(SectionType.EXPERIENCE,
                new OrganisationSection(
                        new Organisation("Organisation11", "http://Organisation11.ru",
                                new Organisation.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organisation.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.addSection(SectionType.EDUCATION,
                new OrganisationSection(
                        new Organisation("Institute", null,
                                new Organisation.Position(1996, Month.JANUARY,2000,Month.DECEMBER, "aspirant", null),
                                new Organisation.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organisation("Organisation11", "http://Organisation11.ru")));


        R2.addContact(ContactType.SKYPE,"skype2");
        R2.addContact(ContactType.PHONE,"+79123422227");
        R1.addSection(SectionType.EXPERIENCE,
                new OrganisationSection(
                        new Organisation("Organisation2", "http://Organisation2.ru",
                                new Organisation.Position(2015, Month.JANUARY, "position2", "content2"))));
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
        assertTrue(newResume == storage.get(UUID_1));
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