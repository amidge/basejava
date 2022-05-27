package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.Month;

class ResumeTestData {

    static public Resume generateResume(String uuid, String fullName) {
        Resume r = new Resume(uuid, fullName);
        switch (uuid) {
            case ("uuid1"):
                r.addContact(ContactType.MAIL, "mail1@ya.ru");
                r.addContact(ContactType.PHONE, "+79103426627");
                r.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
                r.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
                r.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
                r.addSection(SectionType.QUALIFICATION, new ListSection("Java", "SQL", "JavaScript"));
                r.addSection(SectionType.EXPERIENCE,
                        new OrganizationSection(
                                new Organization("Organization11", "http://Organization11.ru",
                                        new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                        new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
                r.addSection(SectionType.EDUCATION,
                        new OrganizationSection(
                                new Organization("Institute", null,
                                        new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                        new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                                new Organization("Organization12", "http://Organization12.ru")));
                 break;
            case ("uuid2"):
                r.addContact(ContactType.SKYPE, "skype2");
                r.addContact(ContactType.PHONE, "+79123422227");
                r.addSection(SectionType.EXPERIENCE,
                        new OrganizationSection(
                                new Organization("Organisation2", "http://Organisation2.ru",
                                        new Organization.Position(2015, Month.JANUARY, "position2", "content2"))));
                break;
        }
        return r;
    }
}
