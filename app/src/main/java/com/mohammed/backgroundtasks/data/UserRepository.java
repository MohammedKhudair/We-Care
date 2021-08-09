package com.mohammed.backgroundtasks.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mohammed.backgroundtasks.data.database.UserDataDao;
import com.mohammed.backgroundtasks.data.database.UserRoomDatabase;
import com.mohammed.backgroundtasks.data.database.UserTimesNotificationTodayDao;
import com.mohammed.backgroundtasks.data.entity.UserData;
import com.mohammed.backgroundtasks.data.entity.UserTimesNotificationToday;

import java.util.List;

public class UserRepository {

    private UserDataDao mUserDataDao;
    private UserTimesNotificationTodayDao mUserTimesNotificationTodayDao;

    private LiveData<List<UserData>> mAllUserData;
    private LiveData<List<UserTimesNotificationToday>> mAllUserTimesNotified;

    public UserRepository(Context context) {
        UserRoomDatabase db = UserRoomDatabase.getInstance(context.getApplicationContext());
        mUserDataDao = db.userDataDao();
        mUserTimesNotificationTodayDao = db.userTimesNotificationTodayDao();
        mAllUserData = mUserDataDao.getAllData();
        mAllUserTimesNotified = mUserTimesNotificationTodayDao.getAllTimesNotified();
    }

    //============================================
    // Get data **********************************
    //============================================

    public LiveData<List<UserData>> getAllUserData() {
        return mAllUserData;
    }

    public LiveData<List<UserTimesNotificationToday>> getAllUserTimesNotified() {
        return mAllUserTimesNotified;
    }

    //============================================
    // Add data **********************************
    //============================================

    public void addUserData(UserData userData) {
        UserRoomDatabase.wrightExecutor.execute(() -> mUserDataDao.addUserData(userData));
    }

    public void addUserTimesNotificationToday(UserTimesNotificationToday timesNotificationToday) {
        UserRoomDatabase.wrightExecutor.execute(() -> mUserTimesNotificationTodayDao.addUserTimesNotified(timesNotificationToday));
    }


    // Delete all data from UserTimesNotificationToday table *
    public void deleteAllUserTimesNotificationToday() {
        UserRoomDatabase.wrightExecutor.execute(() -> mUserTimesNotificationTodayDao.deleteAllTimesNotified());
    }


}
