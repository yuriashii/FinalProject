package com.meiying.bimbel;



public class UserModel {
    public String idbimbel;
    public String uuid;
    public String email;
    public String password;
    public String nama;

    public UserModel(){}
    public UserModel(String uuid,String idbimbel,String nama, String email,String password){
        this.uuid = uuid;
        this.idbimbel = idbimbel;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }
}
