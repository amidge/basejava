package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;

class ResumeTestData {
    private static Resume RESUME;

    static public Resume generateResume(String uuid, String fullName) {
        ContactType contact;














        RESUME = new Resume(uuid, fullName);
        return RESUME;
    }
}
