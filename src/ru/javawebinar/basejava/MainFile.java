package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) {
        String filePath = "D:\\Projects\\topjava.ru\\basejava\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println("fis.read(): " + fis.read());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File rootDir = new File("D:\\Projects\\topjava.ru\\basejava\\src\\ru\\javawebinar\\basejava");
        printDirectoryDeeply(rootDir, 0);

    }

    static public void printDirectoryDeeply(File dir, int level) {
        final int l = level + 1;
        final File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                StringBuilder shift = new StringBuilder();
                for (int i = 1; i < l; i++) {
                    shift.append("    ");
                }
                if (file.isDirectory()) {
                    System.out.println(shift + file.getName() + "     [DIR]");
                    printDirectoryDeeply(file, l);
                } else if (file.isFile()) {
                    System.out.println(shift + file.getName());
                }
            }
        }
    }
}
