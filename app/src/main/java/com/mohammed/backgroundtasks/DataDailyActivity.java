package com.mohammed.backgroundtasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.mohammed.backgroundtasks.data.entity.UserData;
import com.mohammed.backgroundtasks.viewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class DataDailyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView timesOfTheDayTextView;
    Spinner spinner;

    ArrayList<UserData> mUserData = new ArrayList<>();

    List<String> mDates = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_data);
        timesOfTheDayTextView = findViewById(R.id.timesOfTheDay_textView);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        mDates.add("Select a date");

        UserViewModel mViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        mViewModel.getAllUserData().observe(this, userData -> {
            mUserData.addAll(userData);
            // collect all dates in a list
            for (UserData ud : userData) {
                mDates.add(ud.getDate());
            }
        });


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mDates);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String option = parent.getItemAtPosition(position).toString();

        if (!option.equals("Select a date")) {
            int timesNotified = mUserData.get(position - 1).getNotificationRate();
            timesOfTheDayTextView.setText("" + timesNotified);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}