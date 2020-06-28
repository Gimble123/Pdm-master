package com.unisc.pdm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NotificacaoService extends Service {

    boolean isRunning = false;
    boolean isScheduled = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ScheduledThreadPoolExecutor pool =
                new ScheduledThreadPoolExecutor(1);

        if (!isScheduled) {
            long delay = 0;
            long periodo = 10;
            TimeUnit unit = TimeUnit.SECONDS;
            pool.scheduleAtFixedRate(new NotificacaoTask(), delay, periodo, unit);
            isRunning = true;
            isScheduled = true;
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        isScheduled = false;
    }

    class NotificacaoTask implements Runnable {

        @Override
        public void run() {
            if (!estaConectado()) {
                return;
            }
            if (!isRunning) {
                return;
            }
            try {

                URL url = new URL("http://ghelfer.net/la/lastweather.json");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int status = con.getResponseCode();
                if (status == 200) {
                    InputStream stream = new BufferedInputStream(con.getInputStream());
                    BufferedReader br =
                            new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                    con.disconnect();
                    String data = builder.toString();
                    JSONObject res = new JSONObject(data);
                    JSONArray array = res.getJSONArray("weather");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        String temp = obj.getString("temperature");
                        String umidade = obj.getString("humidity");
                        String dt = obj.getString("datetime");
                        criarNotificação(temp, umidade, dt, i);
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private boolean estaConectado() {
            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            return info.isConnected();
        }
    }

    private void criarNotificação(String temp, String umidade, String dt, int id) {

        NotificationManager nm =  (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            nm.createNotificationChannel(mChannel);
        }
        int icone = R.drawable.ic_cloud;
        long data = System.currentTimeMillis();
        String text = temp + "ºC " + umidade + "%";

        Intent i = new Intent(getApplicationContext(), WeatherActivity.class);
        i.putExtra("temp", temp);
        i.putExtra("umidade", umidade);
        i.putExtra("dt", dt);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0, i,0);
        NotificationCompat.Builder build =
                new NotificationCompat.Builder(getApplicationContext(),  "my_channel_01");

        Notification not = build.setContentIntent(pi).setSmallIcon(icone)
                .setAutoCancel(false).setTicker(text).setContentTitle(dt)
                .setContentText(text).setWhen(data).build();
        not.flags = Notification.FLAG_AUTO_CANCEL;
        not.defaults |= Notification.DEFAULT_VIBRATE;
        not.defaults |= Notification.DEFAULT_LIGHTS;
        not.defaults |= Notification.DEFAULT_SOUND;

        nm.notify(id, not);
    }

}









