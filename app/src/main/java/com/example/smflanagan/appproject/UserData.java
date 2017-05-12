package com.example.smflanagan.appproject;

/**
 * Created by smflanagan on 3/28/2017.
 * Test
 */

public class UserData {

    private String user_name;
    private String user_location;

    public UserData(String username) {

        user_name = username;
       // user_location = location;

    }

    public UserData() {

        user_name = "";
        user_location = "";
    }

    public String getUsername() {

        return user_name;

    }

    public String getUserLocation() {

        return user_location;

    }

    public void setUserLocation(String location) {

        user_location = location;
    }

    public void setUsername(String name) {

        user_name = name;
    }


}
