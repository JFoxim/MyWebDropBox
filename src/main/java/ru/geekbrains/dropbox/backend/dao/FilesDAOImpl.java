package ru.geekbrains.dropbox.backend.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Repository
public class FilesDAOImpl implements FilesDAO {
    @Value("${filePath}")
    private String filePath;

    @Override
    public OutputStream getFileOutputStream(String fileName) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        return new FileOutputStream(file);
    }


}
