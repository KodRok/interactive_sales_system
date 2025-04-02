package org.mentor;

import java.io.File;

public class AdapterDetector {
    static Adapter chooseAdapter(String extensionFile) {
        if (extensionFile.equals(".cvs")) {
            return new CsvAdapter();
        }
        return new CustomAdapter();
    }

    static String getExtensionFile(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            throw new IllegalArgumentException("Путь к файлу не является валидным");
        }

        String fileName = file.getName();
        if (fileName.isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не содержит имени файла");
        }

        String extension = "";
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1);
        }
        return extension;
    }
}
