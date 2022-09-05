package com.example.myapplication;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class alarm extends AppCompatActivity {

    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    PendingIntent fdg;
    AlarmManager alarmManager;
    Button onbtn;
    Button offbtn;
    long time;
    Intent notifyIntent;
    private NotificationManager mNotificationManager;
    PendingIntent notifyPendingIntent;
    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        onbtn=(Button) findViewById(R.id.alarmonbtn);
        offbtn=(Button) findViewById(R.id.alarmoffbtn);
//        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        notifyIntent = new Intent(this, AlarmReceiver.class);
        boolean alarmUp = (PendingIntent.getBroadcast(this, 0, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        notifyPendingIntent= PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);




    }
    public void offalarm(View v){
        alarmManager.cancel(notifyPendingIntent);
        Toast.makeText(alarm.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
    }
    public void stopalarm(View v){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (alarmManager==null)
            return;
        Intent myIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        if (myIntent==null)
            return;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 1, myIntent, 0);
        if (pendingIntent==null)
            return;
        alarmManager.cancel(pendingIntent);
//        AlarmManager.AlarmClockInfo fgh =alarmManager.getNextAlarmClock();
//        alarmManager.setAlarmClock(fgh,pendingIntent);
    }

    public void onalarm(View v){
        long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
        Toast.makeText(alarm.this, "ALARM ON", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();

        // calendar is called to get current time in hour and minute
        calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

        // using intent i have class AlarmReceiver class which inherits
        // BroadcastReceiver
        Intent intent = new Intent(alarm.this, AlarmReceiver.class);

//        Intent ytu = new Intent(alarm.this, PollReceiver.class);
        // we call broadcast using pendingIntent
        pendingIntent = PendingIntent.getBroadcast(alarm.this, 0, intent, 0);

//        fdg = PendingIntent.getBroadcast(alarm.this, 0, ytu, 0);

        time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
        if (System.currentTimeMillis() > time) {
            // setting time as AM and PM
            if (calendar.AM_PM == 0)
                time = time + (1000 * 60 * 60 * 12);
            else
                time = time + (1000 * 60 * 60 * 24);
        }
        // Alarm rings continuously until toggle button is turned off
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, notifyPendingIntent);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                time, repeatInterval, notifyPendingIntent);

//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, fdg);
        // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);
    }

}