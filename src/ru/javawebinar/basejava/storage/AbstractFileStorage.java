package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must be not null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }

        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            if (file.createNewFile()) {
                doWrite(r, file);
            }
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }

    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        file.deleteOnExit();
    }

    @Override
    protected List<Resume> doCopyAll() {
        int i = 0;
        final Resume[] resumes = new Resume[size()];
        final File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    resumes[i] = doRead(file);
                } catch (IOException e) {
                    throw new StorageException("IO error", file.getName(), e);
                }
                i++;
            }
        }
        return Arrays.asList(resumes);
    }

    @Override
    public void clear() {
        final File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.deleteOnExit();
            }
        }
    }

    @Override
    public int size() {
        int i = 0;
        final File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                i++;
            }
        }
        return i;
    }
}