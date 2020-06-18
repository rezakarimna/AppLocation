package com.Millionaire.Location.DataBase.DModel.Login;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultUser {

    @SerializedName("result")
    List<FDS_GetUser> resultUser  = new ArrayList<>();

    public List<FDS_GetUser> getResultUser() {
        return resultUser;
    }

    public void setResultUser(List<FDS_GetUser> resultUser) {
        this.resultUser = resultUser;
    }
}
