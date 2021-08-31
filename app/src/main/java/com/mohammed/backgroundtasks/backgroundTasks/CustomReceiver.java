package com.mohammed.backgroundtasks.backgroundTasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.mohammed.backgroundtasks.BuildConfig;
import com.mohammed.backgroundtasks.data.UserRepository;
import com.mohammed.backgroundtasks.data.entity.UserTimesNotificationToday;
import com.mohammed.backgroundtasks.utils.NotificationUtils;
import com.mohammed.backgroundtasks.utils.SharedPreferenceHelper;

import java.util.concurrent.TimeUnit;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        switch (intentAction) {
            case Intent.ACTION_SCREEN_ON:
                cancelWork(context);
                scheduleWork(context);
                SharedPreferenceHelper.screenState(context,true);
                Toast.makeText(context, "Work scheduled", Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_SCREEN_OFF:
                cancelWork(context);
                SharedPreferenceHelper.screenState(context,false);
                Toast.makeText(context, "Work canceled", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void scheduleWork(Context context) {
        SyncWork syncWork = new SyncWork();
        syncWork.scheduleWork(context);
    }

    private void cancelWork(Context context) {
        SyncWork syncWork = new SyncWork();
        syncWork.cancelWork(context);
    }

}