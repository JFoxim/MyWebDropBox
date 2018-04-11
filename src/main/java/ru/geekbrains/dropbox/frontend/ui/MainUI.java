package ru.geekbrains.dropbox.frontend.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.components.grid.ItemClickListener;
import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.dropbox.frontend.service.FilesService;
import ru.geekbrains.dropbox.frontend.ui.view.LoginView;
import ru.geekbrains.dropbox.frontend.ui.view.MainView;


import java.io.*;

@SpringUI
@PushStateNavigation
public class MainUI extends UI {

    Navigator navigator;

    @Autowired
    SpringViewProvider springViewProvider;

    @Override
    public void init(VaadinRequest request) {

        navigator = new Navigator(this, this);

        navigator.addView("",  MainView.class);
        navigator.addView("login", LoginView.class);
    }

}
