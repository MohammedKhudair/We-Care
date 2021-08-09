package com.mohammed.backgroundtasks.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_times_notification_today")
public class UserTimesNotificationToday {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "timesNotificationToday")
   private int timesNotificationToday;

    public UserTimesNotificationToday(int timesNotificationToday) {
        this.timesNotificationToday = timesNotificationToday;
    }

    public int getTimesNotificationToday() {
        return timesNotificationToday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
