package com.mohammed.backgroundtasks.backgroundTasks;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.mohammed.backgroundtasks.data.UserRepository;
import com.mohammed.backgroundtasks.data.entity.UserTimesNotificationToday;
import com.mohammed.backgroundtasks.utils.NotificationUtils;
import com.mohammed.backgroundtasks.utils.SharedPreferenceHelper;

public class NotificationWorker extends Worker {

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {
        // Show the notification
        NotificationUtils.deliverNotification(getApplicationContext());

        // Save data in the database
        UserRepository mRepository = new UserRepository(getApplicationContext());
        mRepository.addUserTimesNotificationToday(new UserTimesNotificationToday(1));

        // Save times notified in a SharedPreference
        SharedPreferenceHelper.saveTimesNotifiedToday(getApplicationContext());
        return Result.success();
    }
}