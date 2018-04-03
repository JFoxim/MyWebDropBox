package ru.geekbrains.dropbox.frontend.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dropbox.frontend.service.FilesService;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
public class FileController {
    @Autowired
    @Qualifier("frontFilesService")
    FilesService service;

    @RequestMapping(
            value = "/files/m1/{file_name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFileMethod1(@PathVariable("file_name") String fileName) {
        return new FileSystemResource(service.getFileByName(fileName));
    }

    @RequestMapping(
            value = "/files/m2/{file_name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public void getFileMethod2(
            @PathVariable("file_name") String fileName,
            HttpServletResponse response
    ) {
        try {
            InputStream inputStream = service.getFileInputStream(fileName);
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
