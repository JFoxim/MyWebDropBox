package ru.geekbrains.dropbox.frontend.dao;

import java.io.*;
import java.util.List;

public interface FilesDAO {
    OutputStream getFileOutputStream(String fileName) throws IOException;
    File getFileByName(String fileName);
    InputStream getFileInputStream(String fileName) throws FileNotFoundException;
    List<File> getFileList();
    void removeFile(File file);
}
