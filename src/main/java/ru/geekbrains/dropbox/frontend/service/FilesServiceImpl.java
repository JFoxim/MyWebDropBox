package ru.geekbrains.dropbox.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geekbrains.dropbox.frontend.model.SavedFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("frontFilesService")
public class FilesServiceImpl implements FilesService {
    @Value("${filePath}")
    private String filePath;

    @Autowired
    ru.geekbrains.dropbox.backend.service.FilesService backService;

    @Override
    public OutputStream getFileOutputStream(String fileName) throws IOException {
        return backService.getFileOutputStream(fileName);
    }

    @Override
    public File getFileByName(String fileName) {
        return new File(filePath + "\\" + fileName);
    }

    @Override
    public InputStream getFileInputStream(String fileName) throws FileNotFoundException {
        return new FileInputStream(new File(filePath + "\\" + fileName));
    }

    @Override
    public List<SavedFile> getFilesFoder(){
        File folder = new File(filePath);
        List<SavedFile> files = new ArrayList<>();
        for (File file : folder.listFiles()){
             SavedFile savedFile = new SavedFile(file.getName(), file.length());
             files.add(savedFile);
        }
        return files;
    }

    @Override
    public SavedFile getSavedFileByName(String fileName, long size){
         return new SavedFile(fileName, size);
    }

    @Override
    public List<SavedFile> deleteSavedFile(Set<SavedFile> selectedSavedFiles, List<SavedFile> allSavedFiles){
        for(SavedFile savedFile : selectedSavedFiles){
            File file = getFileByName(savedFile.getFileName());
            if (file.exists()) {
                file.delete();
                allSavedFiles.remove(savedFile);
            }
        }
        return  allSavedFiles;
    }
}
