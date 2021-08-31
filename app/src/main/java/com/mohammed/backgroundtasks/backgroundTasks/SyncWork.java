package com.mohammed.backgroundtasks.backgroundTasks;

import android.content.Context;
import android.widget.Toast;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class SyncWork {
    WorkManager workManager;

    public void scheduleWork(Context context) {
        Constraints constraints = new Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .build();

        PeriodicWorkRequest workRequest =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 25, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .setInitialDelay(25, TimeUnit.MINUTES)
                        .build();

        workManager = WorkManager.getInstance(context);
        workManager.enqueueUniquePeriodicWork("NotificationWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest);

    }

    public void cancelWork(Context context) {
        if (workManager != null) {
            workManager.cancelAllWork();

        }
    }

    // Sync work after 24 hours
    public void scheduleWorkSync(Context context) {
        PeriodicWorkRequest workRequest =
                new PeriodicWorkRequest.Builder(SyncDataWorker.class, 24, TimeUnit.HOURS)
                        .setInitialDelay(24, TimeUnit.HOURS)
                        .build();

        WorkManager workManager = WorkManager.getInstance(context);
        workManager.enqueueUniquePeriodicWork(
                "SyncDataWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest);
    }


}
