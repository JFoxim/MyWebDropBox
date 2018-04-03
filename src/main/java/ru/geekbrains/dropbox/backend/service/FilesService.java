package ru.geekbrains.dropbox.backend.service;

import java.io.IOException;
import java.io.OutputStream;

public interface FilesService {
    OutputStream getFileOutputStream(String fileName) throws IOException;
}
