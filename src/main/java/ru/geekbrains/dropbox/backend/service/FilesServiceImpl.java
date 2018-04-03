package ru.geekbrains.dropbox.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.dropbox.backend.dao.FilesDAO;

import java.io.IOException;
import java.io.OutputStream;

@Service("backFilesService")
public class FilesServiceImpl implements FilesService {
    @Autowired
    FilesDAO dao;

    @Override
    public OutputStream getFileOutputStream(String fileName) throws IOException {
        return dao.getFileOutputStream(fileName);
    }
}
