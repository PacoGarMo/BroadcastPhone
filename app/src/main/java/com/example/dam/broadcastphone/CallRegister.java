package com.example.dam.broadcastphone;

/**
 * Created by Francisco on 31/01/2018.
 */

public class CallRegister {
    private int id;
    private String type;//Entrante, Saliente y Perdida
    private String number;
    private String date;

    public CallRegister() {
    }

    public CallRegister(int id, String type, String numero, String fechahora) {
        this.id = id;
        this.type = type;
        this.number = numero;
        this.date = fechahora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {return number;}

    public void setNumber(String number) {this.number = number;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
