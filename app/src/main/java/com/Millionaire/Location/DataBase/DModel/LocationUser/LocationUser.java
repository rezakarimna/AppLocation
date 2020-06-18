package com.Millionaire.Location.DataBase.DModel.LocationUser;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LocationUser")
public class LocationUser {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double lat;
    private double lng;
    private String mdate;
    private String mtime;
    private boolean isStateServer;
    private boolean isOpenApp;

    public LocationUser(double lat, double lng, String mdate, String mtime, boolean isStateServer, boolean isOpenApp) {
        this.lat = lat;
        this.lng = lng;
        this.mdate = mdate;
        this.mtime = mtime;
        this.isStateServer = isStateServer;
        this.isOpenApp = isOpenApp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public boolean isStateServer() {
        return isStateServer;
    }

    public void setStateServer(boolean stateServer) {
        isStateServer = stateServer;
    }

    public boolean isOpenApp() {
        return isOpenApp;
    }

    public void setOpenApp(boolean openApp) {
        isOpenApp = openApp;
    }
}
