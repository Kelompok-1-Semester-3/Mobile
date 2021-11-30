package com.example.friendfinderapp.Model;

import java.util.List;

public class ResponseModel {
    public List<SignIn_Model> dataUser;
    public  String pesan;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<SignIn_Model> getDataUser() {
        return dataUser;
    }
    public void setDataUser(List<SignIn_Model> dataUser) {
        this.dataUser = dataUser;
    }
}
