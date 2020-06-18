package com.Millionaire.Location.Setting;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManage {

    private static final String NAME_SHARED_PREF = "shared_pref";
    private SharedPreferences preferences;
    private static final String SAVE_URL = "SAVE_URL";
    private static final String ISLOGIN = "ISLOGIN";
    private static final String TURNONLOCATION = "TurnOnLocation";
    public SharedPrefManage(Context context) {
        preferences = context.getSharedPreferences(NAME_SHARED_PREF , Context.MODE_PRIVATE);
    }

    public void setSaveUrl(String url){
        SharedPreferences.Editor editorUrl = preferences.edit();
        editorUrl.putString(SAVE_URL , url);
        editorUrl.apply();
    }

    public String getSaveUrl(){
        return preferences.getString(SAVE_URL , "");
    }

    public void setIslogin(boolean islogin){
        SharedPreferences.Editor  editorIsLogin = preferences.edit();
        editorIsLogin.putBoolean(ISLOGIN , islogin);
        editorIsLogin.apply();
    }

    public boolean getIsLogin(){
        return preferences.getBoolean(ISLOGIN , false);
    }

    public void setTurnOnLocation(boolean requset){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(TURNONLOCATION , requset);
        editor.apply();
    }
    public boolean getTurnOnLocation(){
        return preferences.getBoolean(TURNONLOCATION , false);
    }


}
