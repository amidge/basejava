package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.DataStreamSerializer;

public class DataParhStorageTest extends AbstractStorageTest {

    public DataParhStorageTest(){
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer() {
        }));
    }

}