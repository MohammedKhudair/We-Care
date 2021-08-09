package com.mohammed.backgroundtasks.backgroundTasks;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.mohammed.backgroundtasks.data.UserRepository;
import com.mohammed.backgroundtasks.data.entity.UserData;
import com.mohammed.backgroundtasks.utils.SharedPreferenceHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SyncDataWorker extends Worker {

    public SyncDataWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String currentDate = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault()).format(new Date());

        UserRepository mRepository = new UserRepository(getApplicationContext());

        // Get the times notified from the SharedPreference
        int timesNotified = SharedPreferenceHelper.getTimesNotifiedToday(getApplicationContext());
        mRepository.addUserData(new UserData(currentDate, timesNotified));

        // Reinitialise data after 24 hours.
        mRepository.deleteAllUserTimesNotificationToday();
        SharedPreferenceHelper.reinitializeTimesNotifiedToday(getApplicationContext());
        return Result.success();
    }
}
