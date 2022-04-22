package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MainCollection {
    protected static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1,"Name1");

    protected static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2,"Name2");

    protected static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3,"Name3");

    protected static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4,"Name4");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());


        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
