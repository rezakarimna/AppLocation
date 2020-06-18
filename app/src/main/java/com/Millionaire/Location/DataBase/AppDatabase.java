package com.Millionaire.Location.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.Millionaire.Location.DataBase.DAccess.LocationUserDA;
import com.Millionaire.Location.DataBase.DAccess.UserDA;
import com.Millionaire.Location.DataBase.DModel.LocationUser.LocationUser;
import com.Millionaire.Location.DataBase.DModel.Login.User;


@Database(entities = {User.class, LocationUser.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "AppDatabase.db";
    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public abstract UserDA getuserDA();

    public abstract LocationUserDA getlocationUser();
}
