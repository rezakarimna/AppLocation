package com.Millionaire.Location.NetWork;

import com.Millionaire.Location.DataBase.DModel.LocationUser.ResultLocationUser;
import com.Millionaire.Location.DataBase.DModel.Login.ResultUser;
import com.Millionaire.Location.DataBase.DModel.Login.User;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("Get_AppVisitor_visitor")
    Call<List<User>> getUser(@Body JsonObject object);
    /*@GET("Get_AppVisitor_visitor/{userId}/{Pass}/{Ime}/{Flag}/")
    Call<ResultUser> getUser(@Path("userId") String userId, @Path("Pass") String Pass,
                             @Path("Ime") String Ime, @Path("Flag") String Flag);*/

    /*@GET("RegisterLocation_XY/{Codev}/{Xlocation}/{YLocation}/{Time}/{date}/{flagOut}/")
    Call<ResultLocationUser> RegisterLocation(@Path("Codev") String Codev, @Path("Xlocation") String Xlocation, @Path("YLocation") String YLocation
            , @Path("Time") String Time, @Path("date") String date, @Path("flagOut") int flagOut);*/

    @POST("SabtLocationVisitor")
    Call<String> RegisterLocation(@Body JsonObject object);

}
