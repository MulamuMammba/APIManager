package com.mammba.APIManager.Model;


public class Users {

    private String email;
    private String password;
    private String name;
    private String surname;
    private String profession;
    private String phoneNum;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Users(String email,String password, String name, String surname, String profession, String phoneNum) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.phoneNum = phoneNum;
    }
    public void addUsers(String email,String password, String name, String surname, String profession, String phoneNum) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.profession = profession;
        this.phoneNum = phoneNum;
    }

}
