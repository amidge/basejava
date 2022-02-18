import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < this.size; i++) {
            this.storage[i] = null;
        }
        this.size = 0;
    }

    void save(Resume r) {
        if (this.size < this.storage.length) {
            this.storage[this.size] = r;
            this.size++;
        }
    }

    Resume get(String uuid) {
        int id = GetIdByUUID(uuid);
        if (id > -1) {
            return this.storage[id];
        } else {
            return null;
        }
    }

    void delete(String uuid) {
        int id = GetIdByUUID(uuid);
        if (id > -1) {
            for (int i = id; i < this.size; i++) {
                if (i < this.storage.length - 1) {
                    this.storage[i] = this.storage[i + 1];
                } else {
                    this.storage[i] = null;
                }
            }
            this.size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(this.storage, this.size);
    }

    int size() {
        return this.size;
    }

    private int GetIdByUUID(String uuid) {
        int id = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.storage[i].uuid.equals(uuid)) {
                id = i;
                break;
            }
        }
        return id;
    }
}
