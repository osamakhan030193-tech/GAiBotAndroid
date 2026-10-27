package com.gaibot.scanner;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.IBinder;

public class CaptureService extends Service {
 private MediaProjection projection;
 @Override public int onStartCommand(Intent intent,int flags,int startId){
  if(Build.VERSION.SDK_INT>=26){NotificationManager nm=getSystemService(NotificationManager.class); nm.createNotificationChannel(new NotificationChannel("gai_capture","GAi BOT screen capture",NotificationManager.IMPORTANCE_LOW)); Notification n=new Notification.Builder(this,"gai_capture").setContentTitle("GAi BOT").setContentText("Screen capture permission active; chart-analysis integration is not yet connected.").setSmallIcon(android.R.drawable.ic_menu_view).build(); startForeground(7,n);}
  if(intent!=null){int code=intent.getIntExtra("resultCode",0); Intent data=intent.getParcelableExtra("data"); if(data!=null){MediaProjectionManager m=(MediaProjectionManager)getSystemService(MEDIA_PROJECTION_SERVICE); try{projection=m.getMediaProjection(code,data);}catch(Exception ignored){}}}
  return START_NOT_STICKY;
 }
 @Override public void onDestroy(){if(projection!=null)projection.stop(); super.onDestroy();}
 @Override public IBinder onBind(Intent i){return null;}
}
