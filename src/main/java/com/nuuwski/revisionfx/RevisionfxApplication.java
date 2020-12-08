package com.nuuwski.revisionfx;

import com.nuuwski.revisionfx.config.UiApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RevisionfxApplication {

    public static void main(String[] args) {
        Application.launch(UiApplication.class, args);
    }

}
