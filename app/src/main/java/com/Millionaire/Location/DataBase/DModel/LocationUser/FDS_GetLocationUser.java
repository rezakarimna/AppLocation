package com.Millionaire.Location.DataBase.DModel.LocationUser;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FDS_GetLocationUser {

    @SerializedName("FDS_Get")
    List<LocationUser> FDS_GetLocationUser = new ArrayList<>();

    public List<LocationUser> getFDS_GetLocationUser() {
        return FDS_GetLocationUser;
    }

    public void setFDS_GetLocationUser(List<LocationUser> FDS_GetLocationUser) {
        this.FDS_GetLocationUser = FDS_GetLocationUser;
    }
}
