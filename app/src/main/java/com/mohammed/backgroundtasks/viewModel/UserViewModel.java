package com.mohammed.backgroundtasks.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mohammed.backgroundtasks.data.UserRepository;
import com.mohammed.backgroundtasks.data.database.UserRoomDatabase;
import com.mohammed.backgroundtasks.data.entity.UserData;
import com.mohammed.backgroundtasks.data.entity.UserTimesNotificationToday;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;
    private LiveData<List<UserData>> mAllUserData;
    private LiveData<List<UserTimesNotificationToday>> mAllUserTimesNotified;


    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUserData = mRepository.getAllUserData();
        mAllUserTimesNotified = mRepository.getAllUserTimesNotified();
    }

    public LiveData<List<UserData>> getAllUserData() {
        return mAllUserData;
    }

    public LiveData<List<UserTimesNotificationToday>> getAllUserTimesNotified() {
        return mAllUserTimesNotified;
    }


}
