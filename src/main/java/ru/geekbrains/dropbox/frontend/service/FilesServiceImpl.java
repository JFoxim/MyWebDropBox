package ru.geekbrains.dropbox.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Service;
import ru.geekbrains.dropbox.frontend.dao.FilesDAO;

import java.io.*;
import java.util.List;

@Service
public class FilesServiceImpl implements FilesService {
    @Autowired
    FilesDAO dao;


    //@PreAuthorize("hasRole('USER')")
    @Override
    public OutputStream getFileOutputStream(String fileName) throws IOException {
        return dao.getFileOutputStream(fileName);
    }

    //@PreAuthorize("hasRole('USER')")
    @Override
    public File getFileByName(String fileName) {
        return dao.getFileByName(fileName);
    }

    //@PreAuthorize("hasRole('USER')")
    @Override
    public InputStream getFileInputStream(String fileName) throws FileNotFoundException {
        return dao.getFileInputStream(fileName);
    }

    //@PreAuthorize("hasRole('USER')")
    public List<File> getFileList(){
        return dao.getFileList();
    }

    //@PreAuthorize("hasRole('USER1')")
    @Override
    public void removeFile(File file) {
        dao.removeFile(file);
    }
}
