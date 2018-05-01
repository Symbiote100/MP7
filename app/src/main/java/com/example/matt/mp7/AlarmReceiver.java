package edu.illinois.cs.cs125.mp7;

import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.widget.Toast;
import android.net.Uri;
import android.media.Ringtone;
import android.media.RingtoneManager;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone alarm = RingtoneManager.getRingtone(context, alarmUri);
        alarm.play();
    }
}
