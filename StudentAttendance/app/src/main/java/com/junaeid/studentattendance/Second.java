package com.junaeid.studentattendance;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.*;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.net.wifi.*;
import java.lang.reflect.*;
import android.content.*;
import android.app.Activity;
import com.example.mylibrary.WifiApManager;
import com.google.android.gms.nearby.*;

public class Second extends Activity{
    WifiApManager wifi;
    private Button doneBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        DoneButton_activity();
        wifi = new WifiApManager(this);
        wifi.showWritePermissionSettings(true);
    }
    public void DoneButton_activity()
    {
        doneBtn = (Button)findViewById(R.id.btn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiManager hot = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                hot.setWifiEnabled(false);
                Hotspot.isApOn(Second.this);
                Hotspot.configApState(Second.this);
                Intent intent = new Intent("com.junaeid.studentattendance.Third" );
                startActivity(intent);
            }
        });
    }
}
