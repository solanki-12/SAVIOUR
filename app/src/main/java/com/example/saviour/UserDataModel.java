package com.example.saviour;

import android.widget.ImageView;

public class UserDataModel {



    String fullName, gender,mobile,purl;

    public UserDataModel() {
    }


    public UserDataModel(String fullName, String gender, String mobile, String purl) {
        this.fullName = fullName;
        this.gender = gender;
        this.mobile = mobile;
        this.purl = purl;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
