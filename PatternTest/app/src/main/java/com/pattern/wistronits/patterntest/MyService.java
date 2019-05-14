package com.pattern.wistronits.patterntest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * @author wistronits
 */
public class MyService extends Service {
    private ShowMsgBinder msgBinder = new ShowMsgBinder();
    private LocalBroadcastManager mLocalBroadcastManager;
    private static final String UPDATE_CHANNEL_ID = "UPDATE_NOTIFY_ID";
    private static final String UPDATE_CHANNEL_NAME = "UPDATE_NOTIFY_NAME";

    public MyService() {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        Log.d("onDestroy", "---------onCreate");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, UPDATE_CHANNEL_ID);
        mBuilder.setContentTitle("前台服务")
                .setContentText("服务持续进行")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setOngoing(true)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(UPDATE_CHANNEL_ID, UPDATE_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(false);
            channel.enableLights(false);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }
        startForeground(1, mBuilder.build());

        super.onCreate();
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d("onDestroy", "---------onBind");
        mLocalBroadcastManager.sendBroadcast(new Intent("com.pattern.wistronits.patterntest"));

        return msgBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("onDestroy", "---------onUnbind");

        return super.onUnbind(intent);
    }

    int i = 0;

    public class ShowMsgBinder extends Binder {

        public void showMsg(int progress, final ProgressCallBack callBack) {

            Log.d("ShowMsgBinder", "---------showMsg");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (i < 100) {

                        try {
                            Thread.sleep(1000);
                            i += 10;

                            callBack.progress(i);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();

        }

        public int showProgress() {
            Log.d("ShowMsgBinder", "---------showProgress");

            return 0;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        Log.d("onDestroy", "---------onDestroy");

    }

    public interface ProgressCallBack {
        int progress(int progress);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d("onRebind", "---------onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("onStart", "---------onStart");

        super.onStart(intent, startId);
    }
}
