package com.example.einore.exercice_integre.model;

public class User {

    private int id;
    private String name;
    private int pin;


    public User() {}

    public User(String name, int pin){

        this.name = name;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}
