package com.mohammed.backgroundtasks.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mohammed.backgroundtasks.data.entity.UserTimesNotificationToday;

import java.util.List;

@Dao
public interface UserTimesNotificationTodayDao {

    @Query("SELECT * FROM user_times_notification_today")
    LiveData<List<UserTimesNotificationToday>> getAllTimesNotified();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addUserTimesNotified(UserTimesNotificationToday notificationToday);

    @Query("DELETE FROM user_times_notification_today")
    void deleteAllTimesNotified();
}
