package com.junaeid.studentattendance;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.mylibrary.WifiApManager;

/**
 * Created by msi on 1/26/2018.
 */

public class Third extends Activity{
    WifiApManager wifi;
    private Button doneBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
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
                Hotspot.isApOn(Third.this);
                Hotspot.configApState(Third.this);
                Intent intent = new Intent("android.intent.action.MAIN" );
                startActivity(intent);
            }
        });
    }
}
