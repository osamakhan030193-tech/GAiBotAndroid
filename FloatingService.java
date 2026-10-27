package com.gaibot.scanner;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.provider.Settings;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

public class FloatingService extends Service {
 private WindowManager wm; private TextView bubble;
 @Override public void onCreate(){ super.onCreate(); if(!Settings.canDrawOverlays(this)) return; wm=(WindowManager)getSystemService(WINDOW_SERVICE); bubble=new TextView(this); bubble.setText("GAi\nSCAN"); bubble.setTextColor(0xff111111); bubble.setTextSize(12); bubble.setGravity(Gravity.CENTER); bubble.setBackgroundColor(0xffffd400); bubble.setPadding(14,12,14,12);
  int type=WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
  WindowManager.LayoutParams p=new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,type,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,PixelFormat.TRANSLUCENT); p.gravity=Gravity.RIGHT|Gravity.CENTER_VERTICAL; p.x=12;
  bubble.setOnClickListener(v->{ Intent i=new Intent(this,MainActivity.class); i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(i); });
  try{wm.addView(bubble,p);}catch(Exception ignored){}
 }
 @Override public int onStartCommand(Intent i,int f,int id){return START_STICKY;}
 @Override public void onDestroy(){if(wm!=null&&bubble!=null)try{wm.removeView(bubble);}catch(Exception ignored){} super.onDestroy();}
 @Override public IBinder onBind(Intent i){return null;}
}
