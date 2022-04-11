package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " isn't exist!");
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) {
            System.out.println("Resume " + r.getUuid() + " already exist!");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insertElement(r, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " isn't exist!");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (i < 0) {
            System.out.println("Resume " + uuid + " isn't exist!");
            return null;
        }
        return storage[i];
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);

    protected abstract int getIndex(String uuid);
}
