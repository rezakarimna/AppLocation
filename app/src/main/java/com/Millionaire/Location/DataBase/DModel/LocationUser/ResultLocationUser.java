package com.Millionaire.Location.DataBase.DModel.LocationUser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultLocationUser {

    @SerializedName("result")
    List<FDS_GetLocationUser> resultLocationUser = new ArrayList<>();

    public List<FDS_GetLocationUser> getResultLocationUser() {
        return resultLocationUser;
    }

    public void setResultLocationUser(List<FDS_GetLocationUser> resultLocationUser) {
        this.resultLocationUser = resultLocationUser;
    }
}

