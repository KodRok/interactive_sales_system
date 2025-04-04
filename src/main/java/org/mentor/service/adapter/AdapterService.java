package org.mentor.service.adapter;

public class AdapterService {
    public OrderAdapter getAdapter(String fileName) {
        String extension = getFileExtension(fileName);
        switch (extension) {
            case "csv":
                return new OrderCsvAdapter();
            case "txt":
                return new OrderCustomAdapter();
            default:
                throw new UnsupportedOperationException("Не поддерживаемый формат файла");
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
        } else {
            return "|";
        }
    }
}
