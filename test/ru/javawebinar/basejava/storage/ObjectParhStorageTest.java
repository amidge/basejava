package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.ObjectStreamSerializer;

public class ObjectParhStorageTest extends AbstractStorageTest {

    public ObjectParhStorageTest(){
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer() {
        }));
    }

}