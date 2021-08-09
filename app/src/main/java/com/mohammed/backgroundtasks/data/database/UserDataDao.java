package com.mohammed.backgroundtasks.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mohammed.backgroundtasks.data.entity.UserData;

import java.util.List;

@Dao
public interface UserDataDao {

    @Query("SELECT * FROM user_data")
   LiveData<List<UserData>>  getAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUserData(UserData user);

}
