package org.mentor.adapter;

import org.mentor.exception.CustomException;

public class OrderAdapterService {
    public static final String ERR_MSG = "Не поддерживаемый формат файла";

    public OrderAdapter getAdapter(String fileName) {
        String extension = getFileExtension(fileName);
        switch (extension) {
            case "csv":
                return new OrderCsvAdapter();
            case "txt":
                return new OrderCustomAdapter();
            default:
                throw new CustomException(ERR_MSG);
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }

    public String getSeparator(String fileName) {
        if (fileName.endsWith(".csv")) {
            return ";";
        }
        return "|";
    }
}
