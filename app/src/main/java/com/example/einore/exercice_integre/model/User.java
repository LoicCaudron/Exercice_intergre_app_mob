package com.example.einore.exercice_integre.model;

public class User {

    private int id;
    private String name;
    private int pin;
    private float T_min;
    private float T_max;
    private float Humidity;
    private float battery;
    private String num;


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

    public float getT_min() {
        return T_min;
    }

    public void setT_min(float t_min) {
        T_min = t_min;
    }

    public float getT_max() {
        return T_max;
    }

    public void setT_max(float t_max) {
        T_max = t_max;
    }

    public float getHumidity() {
        return Humidity;
    }

    public void setHumidity(float humidity) {
        Humidity = humidity;
    }

    public float getBattery() {
        return battery;
    }

    public void setBattery(float battery) {
        this.battery = battery;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


}
