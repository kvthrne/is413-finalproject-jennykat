package com.example.finalproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class PhoneBookController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        PhoneBookModel phoneBookModel = new PhoneBookModel();
        PhoneBookView phoneBookView = new PhoneBookView(phoneBookModel);
        phoneBookView.createAndShowGUI(stage);

    }
}
