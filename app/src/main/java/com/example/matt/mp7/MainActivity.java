package edu.illinois.cs.cs125.mp7;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Calendar;

import android.view.View;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    private TimePicker alarmTimePicker;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load the main layout for our activity
        setContentView(R.layout.activity_main);

        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


    }

    public void onToggleClicked(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(MainActivity.this, "Alarm is on", Toast.LENGTH_LONG);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            time = calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000);

            if (System.currentTimeMillis() > time) {
                if (calendar.AM_PM == 0) {
                    time = time + (12 * 60 * 60 * 1000);
                } else {
                    time = time + (24 * 60 * 60 * 1000);
                }
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
            } else {
                alarmManager.cancel(pendingIntent);
                Toast.makeText(MainActivity.this, "Alarm is off", Toast.LENGTH_LONG);
            }

        }

    }


}
