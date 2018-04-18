package ru.geekbrains.dropbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@ComponentScan
public class DropboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DropboxApplication.class, args);
    }
}
