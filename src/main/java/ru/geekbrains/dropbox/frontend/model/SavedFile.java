package ru.geekbrains.dropbox.frontend.model;

import ru.geekbrains.dropbox.frontend.service.FilesServiceImpl;

public class SavedFile {
    private FilesServiceImpl filesService = new FilesServiceImpl();
    private String fileName;
    private long size;


    public String getFileName(){
        return  fileName;
    }

    public long getSize() {
        return size;
    }

    public SavedFile(String fileName, long size){
        this.fileName = fileName;
        this.size = size;
    }

}
