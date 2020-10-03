package com.health.healthbloom.Databases;

public class UserDatabaseClass {

    String fullName, userName, email, password, gender, dob, phoneNum;

    public UserDatabaseClass(){}

    public UserDatabaseClass(String fullName, String userName, String email, String password, String gender, String dob, String phoneNum) {

        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.phoneNum = phoneNum;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
