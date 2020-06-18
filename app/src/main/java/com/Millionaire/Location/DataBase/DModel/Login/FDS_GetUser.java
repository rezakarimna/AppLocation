package com.Millionaire.Location.DataBase.DModel.Login;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FDS_GetUser {

    @SerializedName("FDS_Get")
    List<User> FDS_GetUser = new ArrayList<>();

    public List<User> getFDS_GetUser() {
        return FDS_GetUser;
    }

    public void setFDS_GetUser(List<User> FDS_GetUser) {
        this.FDS_GetUser = FDS_GetUser;
    }
}
