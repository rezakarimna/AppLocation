package com.Millionaire.Location.DataBase.DAccess;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.Millionaire.Location.DataBase.DModel.Login.User;

import java.util.List;

@Dao
public interface UserDA {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void SetInsert(User item);

    @Query("DELETE FROM User")
    void deleteAll();


}
