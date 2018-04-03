package ru.geekbrains.dropbox.backend.dao;

import java.io.IOException;
import java.io.OutputStream;

public interface FilesDAO {
    OutputStream getFileOutputStream(String fileName) throws IOException;
}
