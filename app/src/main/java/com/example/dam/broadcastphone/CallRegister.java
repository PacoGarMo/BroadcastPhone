package com.example.dam.broadcastphone;

/**
 * Created by Francisco on 31/01/2018.
 */

public class CallRegister {
    String date, hour, state, phone;
    int id;

    public CallRegister() {
    }

    public CallRegister(String date, String hour, String state, String phone) {
        this.date = date;
        this.hour = hour;
        this.state = state;
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CallRegister{" +
                "date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", state='" + state + '\'' +
                ", phone=" + phone +
                ", id=" + id +
                '}';
    }
}
