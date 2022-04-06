package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int i = findIndex(r.getUuid());
        if (i == -1) {
            System.out.println("Resume " + r.getUuid() + " isn't exist!");
        } else {
            storage[i] = r;
        }
    }

    public void save(Resume r) {
        if (findIndex(r.getUuid()) != -1) {
            System.out.println("Resume " + r.getUuid() + " already exist!");
        } else if (size == storage.length) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int i = findIndex(uuid);
        if (i == -1) {
            System.out.println("Resume " + uuid + " isn't exist!");
            return null;
        }
        return storage[i];
    }

    public void delete(String uuid) {
        int i = findIndex(uuid);
        if (i == -1) {
            System.out.println("Resume " + uuid + " isn't exist!");
        } else {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
