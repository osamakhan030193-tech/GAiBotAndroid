package com.gaibot.scanner;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
  private static final int REQ_CAPTURE = 4401;
  @Override public void onCreate(Bundle b) {
    super.onCreate(b);
    LinearLayout root = new LinearLayout(this); root.setOrientation(1); root.setPadding(32,48,32,32); root.setGravity(Gravity.CENTER_VERTICAL);
    root.setBackgroundColor(0xff101010);
    TextView title = new TextView(this); title.setText("GAi BOT Android"); title.setTextColor(0xffffd400); title.setTextSize(28); root.addView(title);
    TextView info = new TextView(this); info.setText("Floating popup + screen-capture permission prototype. The original extension signal engine still needs Android integration and testing."); info.setTextColor(0xffffffff); info.setTextSize(16); info.setPadding(0,20,0,24); root.addView(info);
    Button overlay = new Button(this); overlay.setText("1. Allow display over other apps"); root.addView(overlay);
    overlay.setOnClickListener(v -> { if (!Settings.canDrawOverlays(this)) { Intent i = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName())); startActivity(i); } else startFloating(); });
    Button capture = new Button(this); capture.setText("2. Start screen capture permission"); root.addView(capture);
    capture.setOnClickListener(v -> { MediaProjectionManager m=(MediaProjectionManager)getSystemService(MEDIA_PROJECTION_SERVICE); startActivityForResult(m.createScreenCaptureIntent(), REQ_CAPTURE); });
    setContentView(root);
  }
  @Override protected void onResume() { super.onResume(); if (Settings.canDrawOverlays(this)) startFloating(); }
  private void startFloating() { try { startService(new Intent(this, FloatingService.class)); } catch(Exception ignored) {} }
  @Override protected void onActivityResult(int r,int c,Intent d) { super.onActivityResult(r,c,d); if(r==REQ_CAPTURE && c==RESULT_OK && d!=null) { Intent s=new Intent(this,CaptureService.class); s.putExtra("resultCode",c); s.putExtra("data",d); startForegroundService(s); } }
}
