package com.mohammed.backgroundtasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammed.backgroundtasks.utils.NotificationUtils;
import com.mohammed.backgroundtasks.backgroundTasks.NotificationWorker;
import com.mohammed.backgroundtasks.backgroundTasks.SyncDataWorker;
import com.mohammed.backgroundtasks.data.entity.UserData;
import com.mohammed.backgroundtasks.data.entity.UserTimesNotificationToday;
import com.mohammed.backgroundtasks.viewModel.UserViewModel;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView todayAlertsTextView;
    TextView weeklyAverageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todayAlertsTextView = findViewById(R.id.notified_today_TextView);
        weeklyAverageTextView = findViewById(R.id.weekly_avg_TextView);
        // Create the NotificationChannel.
        NotificationUtils.createNotificationChannel(this);

        // Initialise ViewModel
        UserViewModel mViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        // Set times notified of the day
        mViewModel.getAllUserTimesNotified().observe(this, userTimesNotificationToday -> {
            if (userTimesNotificationToday != null) {
                int timesNotified = 0;

                for (UserTimesNotificationToday utnt : userTimesNotificationToday) {
                    timesNotified += utnt.getTimesNotificationToday();
                }
                todayAlertsTextView.setText("" + timesNotified);
            }
        });


        // Set average of the week
        mViewModel.getAllUserData().observe(this, userData -> {
            if (userData != null) {
                int allTimesNotified = 0;
                int days = 0;

                for (UserData ud : userData) {
                    allTimesNotified += ud.getNotificationRate();
                    days++;
                }

                if (days != 0) {
                    weeklyAverageTextView.setText("" + allTimesNotified / days ); // I know  i cane organize this with a resource string 😁
                }
            }
        });


        scheduleWork(); // schedule work times notified today
        scheduleWorkSync(); // Sync work after 24 hours
    }


    private void scheduleWork() {

        Constraints constraints = new Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .build();

        PeriodicWorkRequest workRequest =
                new PeriodicWorkRequest.Builder(NotificationWorker.class, 25, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .setInitialDelay(25, TimeUnit.MINUTES)
                        .build();

        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueueUniquePeriodicWork(
                "NotificationWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest);
    }

    private void scheduleWorkSync() {
        PeriodicWorkRequest workRequest =
                new PeriodicWorkRequest.Builder(SyncDataWorker.class, 24, TimeUnit.HOURS)
                        .setInitialDelay(24, TimeUnit.HOURS)
                        .build();

        WorkManager workManager = WorkManager.getInstance(this);
        workManager.enqueueUniquePeriodicWork(
                "SyncDataWorker",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest);
    }

    public void showDailyData(View view) {
        startActivity(new Intent(MainActivity.this, DataDailyActivity.class));
    }

}
