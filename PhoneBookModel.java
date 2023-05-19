package com.example.finalproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PhoneBookModel {
    private final ObservableList<Person> data = FXCollections.observableArrayList(
            //default info
            new Person("Jenny Lam", "4434611234", "jennyl1@umbc.edu", "jennylam@gmail.com"),
            new Person("Kat De Guzman", "4434601234", "k180@umbc.edu", "katdeguzman@gmail.com"));

    public ObservableList<Person> getData() {
        return data;
    }

    public void addPerson(Person person) {
        data.add(person);
    }

    public void removePerson(Person person) {
        data.remove(person);
    }

    public static class Person {
        private final SimpleStringProperty contactName;
        private final SimpleStringProperty contactNumber;
        private final SimpleStringProperty primaryEmail;
        private final SimpleStringProperty secondaryEmail;

        public Person(String cName, String cNum, String pemail, String semail) {
            this.contactName = new SimpleStringProperty(cName);
            this.contactNumber = new SimpleStringProperty(cNum);
            this.primaryEmail = new SimpleStringProperty(pemail);
            this.secondaryEmail = new SimpleStringProperty(semail);
        }

        public String getContactName() {
            return contactName.get();
        }

        public void setContactName(String fName) {
            contactName.set(fName);
        }

        public String getContactNumber() {
            return contactNumber.get();
        }

        public void setContactNumber(String fName) {
            contactNumber.set(fName);
        }

        public String getPrimaryEmail() {
            return primaryEmail.get();
        }

        public void setPrimaryEmail(String fName) {
            primaryEmail.set(fName);
        }

        public String getSecondaryEmail() {
            return secondaryEmail.get();
        }

        public void setSecondaryEmail(String fName) {
            secondaryEmail.set(fName);
        }
    }
}
