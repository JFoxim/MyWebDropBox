package ru.geekbrains.dropbox.frontend.ui;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import ru.geekbrains.dropbox.frontend.model.SavedFile;
import ru.geekbrains.dropbox.frontend.service.AuthService;
import ru.geekbrains.dropbox.frontend.service.FilesService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;

@SpringUI
public class MainUI extends UI {

    @Autowired
    AuthService authService;
    @Autowired
    @Qualifier("frontFilesService")
    FilesService filesService;

    private boolean authentication = false;
    private Panel pnlAutheticate;
    private List<SavedFile> files;
    private Grid<SavedFile> gridFiles;
    private Button btnDelete;
    private Upload uploadFile;
    private Button btnDownload;
    private Panel pnlActions;
    private static FileDownloader fd;
    private static FileResource res;


    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layoutSource = new VerticalLayout();
        layoutSource.setSizeUndefined();

        gridFiles = new Grid<>();
        gridFiles.setSizeFull();
        files = filesService.getFilesFoder();
        gridFiles.setItems(files);
        gridInit(gridFiles);

        pnlAutheticate = new Panel("Введите логин и пароль");
        pnlAutheticate.setContent(authLayout());
        pnlAutheticate.setSizeUndefined();

        pnlActions = new Panel();
        pnlActions.setSizeUndefined();

        uploadFile = new Upload();
        uploadFile.setButtonCaption("Загрузить");
        uploadFile.setReceiver(new Upload.Receiver() {
            @Override
            public OutputStream receiveUpload(String fileName, String mimeType) {
                try {
                    SavedFile savedFile = filesService.getSavedFileByName(fileName, uploadFile.getUploadSize());
                    files.add(savedFile);
                    gridFiles.setItems(files);
                    return filesService.getFileOutputStream(fileName);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    Notification.show("Не удалось загрузить файл!").setDelayMsec(1000);
                }
                return null;
            }
        });

         btnDelete = new Button("Удалить", clickEvent ->{
            if (gridFiles.getSelectedItems() != null) {
                filesService.deleteSavedFile(gridFiles.getSelectedItems(), files);
                gridFiles.setItems(files);
            }
        });

        btnDownload = new Button("Скачать");
        File file = new File("");
        res = new FileResource(file);
        fd = new FileDownloader(res);
        fd.extend(btnDownload);

        gridFiles.setVisible(false);

        HorizontalLayout layoutActions = new HorizontalLayout();
        layoutActions.setSizeUndefined();
        pnlActions.setVisible(false);

        pnlActions.setContent(layoutActions);
        layoutActions.addComponents(uploadFile, btnDelete, btnDownload);
        layoutSource.addComponents(gridFiles, pnlAutheticate, pnlActions);
        this.setContent(layoutSource);
    }

    private void gridInit(com.vaadin.ui.Grid<SavedFile>  gridFiles)
    {
        gridFiles.addColumn(SavedFile::getFileName).setCaption("Имя файла");
        gridFiles.addColumn(SavedFile::getSize).setCaption("Размер");

        gridFiles.addSelectionListener(new SelectionListener<SavedFile>() {
            @Override
            public void selectionChange(SelectionEvent<SavedFile> event) {
                for(SavedFile savedFile : event.getAllSelectedItems()){
                    File file = filesService.getFileByName(savedFile.getFileName());
                    res = new FileResource(file);
                    fd.setFileDownloadResource(res);
                }
            }
        });
    }

    private HorizontalLayout authLayout() {
        HorizontalLayout authLayout = new HorizontalLayout();
        TextField loginTextField = new TextField();
        loginTextField.setPlaceholder("Login");
        PasswordField passwordField = new PasswordField();
        passwordField.setPlaceholder("Password");
        Button btnLogin = new Button("Войти");
        btnLogin.addClickListener(clickEvent -> {
            this.authentication = authService.login(loginTextField.getValue(), passwordField.getValue());
            if (this.authentication) {
                pnlActions.setVisible(true);
                loginTextField.setVisible(false);
                passwordField.setVisible(false);
                btnLogin.setVisible(false);
                gridFiles.setVisible(true);
                gridFiles.setWidth("400");
                pnlAutheticate.setWidth("400");
                pnlAutheticate.setCaption("Вы вошли как "+loginTextField.getValue().toString());
            }
            else {
                pnlAutheticate.setCaption("Не верный логин или пароль");
            }
        });
        authLayout.addComponents(loginTextField, passwordField, btnLogin);
        return authLayout;
    }
}
