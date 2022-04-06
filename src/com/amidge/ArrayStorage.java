package com.amidge;
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
        int i = findIndex(r.uuid);
        if (i == -1) {
            System.out.println("ERROR: Resume[" + r.uuid + "] isn't exist!");
        } else {
            storage[i] = r;
        }
    }

    public void save(Resume r) {
        if (findIndex(r.uuid) != -1) {
            System.out.println("ERROR: Resume[" + r.uuid + "] already is exist!");
        } else if (size == storage.length) {
            System.out.println("ERROR: Storage overflow");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int i = findIndex(uuid);
        if (i == -1) {
            System.out.println("ERROR: Resume[" + uuid + "] isn't exist!");
            return null;
        }
        return storage[i];
    }

    public void delete(String uuid) {
        int i = findIndex(uuid);
        if (i == -1) {
            System.out.println("ERROR: Resume[" + uuid + "] isn't exist!");
        } else {
            storage[i] = storage[size - 1];
            storage[size - 1] = null;
            size--;
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
