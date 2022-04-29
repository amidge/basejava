package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;

import java.time.Month;

class ResumeTestData {

    static public Resume generateResume(String uuid, String fullName) {
        Resume r = new Resume(uuid, fullName);
        switch (uuid) {
            case ("uuid1"):
                r.addContact(ContactType.MAIL, "mail11@reg.ru");
                r.addContact(ContactType.PHONE, "+79103426627");
                r.addSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
                r.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
                r.addSection(SectionType.ACHIEVEMENT, new TextSection("Achievement1"));
                r.addSection(SectionType.QUALIFICATION, new TextSection("Qualification1"));
                r.addSection(SectionType.EXPERIENCE,
                        new OrganisationSection(
                                new Organisation("Organisation11", "http://Organisation11.ru",
                                        new Organisation.Position(2005, Month.JANUARY, "position1", "content1"),
                                        new Organisation.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
                r.addSection(SectionType.EDUCATION,
                        new OrganisationSection(
                                new Organisation("Institute", null,
                                        new Organisation.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                        new Organisation.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                                new Organisation("Organisation11", "http://Organisation11.ru")));
                break;
            case ("uuid2"):
                r.addContact(ContactType.SKYPE, "skype2");
                r.addContact(ContactType.PHONE, "+79123422227");
                r.addSection(SectionType.EXPERIENCE,
                        new OrganisationSection(
                                new Organisation("Organisation2", "http://Organisation2.ru",
                                        new Organisation.Position(2015, Month.JANUARY, "position2", "content2"))));
                break;
        }
        return r;
    }
}
