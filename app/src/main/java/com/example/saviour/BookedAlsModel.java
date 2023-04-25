package com.example.saviour;

public class BookedAlsModel {

    String fullName, mobile, ambulanceType,useraddress, purl;


    public BookedAlsModel() {
    }

    public BookedAlsModel(String fullName, String mobile, String ambulanceType, String useraddress, String purl) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.ambulanceType = ambulanceType;
        this.useraddress = useraddress;
        this.purl = purl;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAmbulanceType() {
        return ambulanceType;
    }

    public void setAmbulanceType(String ambulanceType) {
        this.ambulanceType = ambulanceType;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
