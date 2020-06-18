package com.Millionaire.Location.DataBase.DAccess;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.Millionaire.Location.DataBase.DModel.LocationUser.LocationUser;

import java.util.List;

@Dao
public interface LocationUserDA {

    @Query("SELECT * FROM LocationUser")
    List<LocationUser> getAll();

    @Insert
    void SetInsert(LocationUser item);

    @Update
    void SetUpdate(LocationUser item);

    @Query("DELETE FROM LocationUser")
    void deleteAll();

    @Query("UPDATE LocationUser SET isStateServer = :isStateServer WHERE id = :id")
    void update(boolean isStateServer, int id);

    @Query("SELECT * FROM LocationUser WHERE isStateServer = :isStateServer")
    List<LocationUser> getListSendServer(boolean isStateServer);

}
