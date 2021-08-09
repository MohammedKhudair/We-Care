package com.mohammed.backgroundtasks.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_data")
public class UserData {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "notification_rate")
    int notificationRate;

    public UserData(String date, int notificationRate) {
        this.date = date;
        this.notificationRate = notificationRate;
    }

    public String getDate() {
        return date;
    }

    public int getNotificationRate() {
        return notificationRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
