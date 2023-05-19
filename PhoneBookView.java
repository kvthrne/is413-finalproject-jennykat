package com.example.finalproject;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PhoneBookView {
    private final PhoneBookModel phoneBook;
    private final TableView<PhoneBookModel.Person> table = new TableView<>();


    public PhoneBookView(PhoneBookModel phoneBook) {
        this.phoneBook = phoneBook;
    }

    //creates and shows GUI
    public void createAndShowGUI(Stage stage) {
        Scene scene = new Scene(new Group());
        //stylesheet to add style
        String css = this.getClass().getResource("/css/style1.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("My Phone Book");
        stage.setWidth(720);
        stage.setHeight(600);


        final Label label = new Label("Phone Book");

        table.setEditable(true);

        //table column for the contact name
        TableColumn<PhoneBookModel.Person, String> contactName = new TableColumn<>("Contact Name");
        contactName.setMinWidth(150);
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactName.setCellFactory(TextFieldTableCell.forTableColumn());
        contactName.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow())
                        .setContactName(event.getNewValue())
        );

        //table column for the contact number
        TableColumn<PhoneBookModel.Person, String> contactNumber = new TableColumn<>("Contact Number");
        contactNumber.setMinWidth(150);
        contactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        contactNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        contactNumber.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow())
                        .setContactNumber(event.getNewValue())
        );

        //table column for the contact's primary email
        TableColumn<PhoneBookModel.Person, String> primaryEmail = new TableColumn<>("Primary Email");
        primaryEmail.setMinWidth(200);
        primaryEmail.setCellValueFactory(new PropertyValueFactory<>("primaryEmail"));
        primaryEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        primaryEmail.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow())
                        .setPrimaryEmail(event.getNewValue())
        );

        //table column for the contact's secondary email
        TableColumn<PhoneBookModel.Person, String> secondaryEmail = new TableColumn<>("Secondary Email");
        secondaryEmail.setMinWidth(200);
        secondaryEmail.setCellValueFactory(new PropertyValueFactory<>("secondaryEmail"));
        secondaryEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        secondaryEmail.setOnEditCommit(event ->
                event.getTableView().getItems().get(event.getTablePosition().getRow())
                        .setSecondaryEmail(event.getNewValue())
        );

        table.setItems(phoneBook.getData());
        table.getColumns().addAll(contactName, contactNumber, primaryEmail, secondaryEmail);

        //text boxes on bottom for user to add new contact information
        final TextField addContactName = new TextField();
        addContactName.setPromptText("Contact Name");
        addContactName.setMaxWidth(contactName.getPrefWidth() + 15);

        final TextField addContactNumber = new TextField();
        addContactNumber.setMaxWidth(contactNumber.getPrefWidth() + 28);
        addContactNumber.setPromptText("Contact Number");

        final TextField addPrimaryEmail = new TextField();
        addPrimaryEmail.setMaxWidth(primaryEmail.getPrefWidth() + 15);
        addPrimaryEmail.setPromptText("Primary Email");

        final TextField addSecondaryEmail = new TextField();
        addSecondaryEmail.setMaxWidth(secondaryEmail.getPrefWidth() + 28);
        addSecondaryEmail.setPromptText("Secondary Email");

        //after the user is done entering information, they can press the 'add' button to add it to the table
        final Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            phoneBook.addPerson(new PhoneBookModel.Person(
                    addContactName.getText(),
                    addContactNumber.getText(),
                    addPrimaryEmail.getText(),
                    addSecondaryEmail.getText()));
            addContactName.clear();
            addContactNumber.clear();
            addPrimaryEmail.clear();
            addSecondaryEmail.clear();
        });

        //after the user selects a row, they can press the delete button to remove it from the table
        final Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            PhoneBookModel.Person selectedPerson = table.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                phoneBook.removePerson(selectedPerson);
            }
        });

        HBox hb = new HBox(addContactName, addContactNumber, addPrimaryEmail, addSecondaryEmail, addButton, deleteButton);
        hb.setSpacing(3);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table, hb);
        vbox.setId("phonebook-table");

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
