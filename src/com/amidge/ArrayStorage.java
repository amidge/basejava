package com.amidge;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size,null);
        size = 0;
    }

    void update(Resume r) {
        int i = findIndex(r.uuid);
        if (i > -1) {
            storage[i] = r;
        } else {
            System.out.println("ERROR: Resume[" + r.uuid + "] isn't exist!");
        }
    }

    void save(Resume r) {
        int i = findIndex(r.uuid);
        if (i == -1) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            }
        } else {
            System.out.println("ERROR: Resume[" + i + "] is exist!");
        }
    }

    Resume get(String uuid) {
        int i = findIndex(uuid);
        if (i > -1) {
            return storage[i];
        } else {
            System.out.println("ERROR: Resume[" + uuid + "] isn't exist!");
            return null;
        }
    }

    void delete(String uuid) {
        int i = findIndex(uuid);
        if (i > -1) {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: Resume[" + uuid + "] isn't exist!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
