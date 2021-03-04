package com.smartspot.issuetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends Helper {

    TextView dayTV, dateTV, dateFilterTV, filterByTV, iosTV, xcodeTV;
    Calendar myCalendar, calendar;
    DatePickerDialog.OnDateSetListener date;
    private String timeStamp, dayToday, daySelected, monthToday, monthSelected, yearToday, yearSelected, dayOfWeekToday, dayOfWeekSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        widgetHooks();
        getCurrentDate();
        datePickerDialog();
        onClicks();
    }

    private void getCurrentDate() {
        timeStamp = ""+System.currentTimeMillis();
        calendar.setTimeInMillis(Long.parseLong(timeStamp));
        dayOfWeekToday = DateFormat.format("EEEE", calendar).toString().trim();
        dayToday = DateFormat.format("dd", calendar).toString();
        monthToday = DateFormat.format("MMMM", calendar).toString().trim();
        yearToday = DateFormat.format("yyyy", calendar).toString();

        dayTV.setText("Today");
        dateTV.setText(""+dayOfWeekToday+", "+dayToday+" "+monthToday);
    }

    private void widgetHooks() {
        dayTV = findViewById(R.id.day_textView);
        dateTV = findViewById(R.id.date_textView);
        dateFilterTV = findViewById(R.id.date1_textView);
        filterByTV = findViewById(R.id.filter_by_textView);
        iosTV = findViewById(R.id.ios_textView);
        xcodeTV = findViewById(R.id.xcode_textView);

        myCalendar = Calendar.getInstance();
        calendar = Calendar.getInstance();
    }
    private void onClicks() {
        dateFilterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void datePickerDialog() {
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                updateDayTextView();
            }
        };
    }

    private void updateLabel() {
        String dayOfWeekFormat = "EEEE"; //In which you need put here
        String dayFormat = "dd";
        String monthFormat = "MMMM";
        SimpleDateFormat sdfDOW = new SimpleDateFormat(dayOfWeekFormat);
        SimpleDateFormat sdfD = new SimpleDateFormat(dayFormat);
        SimpleDateFormat sdfM = new SimpleDateFormat(monthFormat);

        daySelected = sdfD.format(myCalendar.getTime());
        dayOfWeekSelected = sdfDOW.format(myCalendar.getTime()).trim();
        monthSelected = sdfM.format(myCalendar.getTime()).trim();
        dateTV.setText(""+dayOfWeekSelected+", "+daySelected+" "+monthSelected);
        updateDayTextView();
    }

    private void updateDayTextView() {
        if (dayOfWeekSelected.toLowerCase() != dayOfWeekToday.toLowerCase()){
            dayTV.setText("");
            makeToast("selected"+dayOfWeekSelected);
            makeToast("today"+dayOfWeekToday);
        }
    }
}