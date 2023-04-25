package com.example.saviour;

public class ReadWriteUserDetails {
    public String fullName, doB, gender, mobile;
    public String ambulanceType;


    //constructor
    public ReadWriteUserDetails(){};
    public ReadWriteUserDetails(String textFullName, String textDoB, String textGender, String textMobile)
    {
        this.fullName = textFullName;
        this.doB = textDoB;
        this.gender = textGender;
        this.mobile = textMobile;
    }

    public String getAmbulanceType() {
        return ambulanceType;
    }

    public void setAmbulanceType(String ambulanceType) {
        this.ambulanceType = ambulanceType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDoB() {
        return doB;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
