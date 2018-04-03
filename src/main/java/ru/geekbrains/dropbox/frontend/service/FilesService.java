package ru.geekbrains.dropbox.frontend.service;

import ru.geekbrains.dropbox.frontend.model.SavedFile;

import java.io.*;
import java.util.List;
import java.util.Set;

public interface FilesService {
    OutputStream getFileOutputStream(String fileName) throws IOException;
    File getFileByName(String fileName);
    InputStream getFileInputStream(String fileName) throws FileNotFoundException;
    List<SavedFile> getFilesFoder();
    SavedFile getSavedFileByName(String fileName, long size);
    List<SavedFile> deleteSavedFile(Set<SavedFile> selectedSavedFiles, List<SavedFile> allSavedFiles);
}
