package com.mohammed.backgroundtasks.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mohammed.backgroundtasks.data.entity.UserData;
import com.mohammed.backgroundtasks.data.entity.UserTimesNotificationToday;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserData.class , UserTimesNotificationToday.class}, version = 2, exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    public abstract UserDataDao userDataDao();
    public abstract UserTimesNotificationTodayDao userTimesNotificationTodayDao();


    private static UserRoomDatabase INSTANCE;
    public static final ExecutorService wrightExecutor = Executors.newFixedThreadPool(4);

    public static UserRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UserRoomDatabase.class,
                            "user_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
