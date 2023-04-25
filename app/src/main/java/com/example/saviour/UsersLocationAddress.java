package com.example.saviour;

public class UsersLocationAddress {
    String useraddress;
    String AmbulanceType;

    public UsersLocationAddress(String useraddress, String ambulanceType) {
        this.useraddress = useraddress;
        AmbulanceType = ambulanceType;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public String getAmbulanceType() {
        return AmbulanceType;
    }
}
