package com.example.user.demo;

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
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.nearby.connection.PayloadTransferUpdate;
import com.google.android.gms.nearby.connection.Strategy;

public class MainActivity extends AppCompatActivity {
    private static final Strategy STRATEGY = Strategy.P2P_STAR;
    private static final String TAG = "Demo";
    private ConnectionsClient client;
    private Button sendbtn;
    private String text;
    private static Button btn,wificlose,hotspoton,hotspotof;
    WifiApManager wifi;
    private EditText msg2;
    private String New_id;
    private TextView setmsg,result;


    private final PayloadCallback payloadCallback = new PayloadCallback() {
        @Override
        public void onPayloadReceived(String ID, Payload payload) {
            setmsg.setText(payload.asBytes().toString());
        }

        @Override
        public void onPayloadTransferUpdate(String s, PayloadTransferUpdate payloadTransferUpdate) {
            if(payloadTransferUpdate.getStatus() == PayloadTransferUpdate.Status.SUCCESS)
            {
                //setmsg.setText(text.getBytes().toString());
                Toast m = Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG);
                m.show();
            }
        }
    };
    private final EndpointDiscoveryCallback endpointDiscoveryCallback = new EndpointDiscoveryCallback() {
        @Override
        public void onEndpointFound(String id, DiscoveredEndpointInfo discoveredEndpointInfo) {
            Log.i(TAG, "onEndpointFound: endpoint found, connecting");
            client.requestConnection(text,id, connectionLifecycleCallback);
        }

        @Override
        public void onEndpointLost(String s) {

        }
    };
    private final ConnectionLifecycleCallback connectionLifecycleCallback = new ConnectionLifecycleCallback() {
        @Override
        public void onConnectionInitiated(String id, ConnectionInfo connectionInfo) {
                Log.i(TAG,"onConnectionInitiated: Accepting Connection");
                client.acceptConnection(id,payloadCallback);
        }

        @Override
        public void onConnectionResult(String id, ConnectionResolution connectionResolution) {
                if(connectionResolution.getStatus().isSuccess())
                {
                    client.stopDiscovery();
                    client.stopAdvertising();
                    New_id = id;
                    buttonstate(true);

                }
        }

        @Override
        public void onDisconnected(String s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifion();
        wifiof();
        sendbtn = (Button)findViewById(R.id.send);
        wifi = new WifiApManager(this);
        wifi.showWritePermissionSettings(true);
        setHotspoton();
        msg2 = (EditText) findViewById(R.id.f1);
        // text = msg2.getText().toString();
        setmsg = (TextView)findViewById(R.id.msg);
        result = (TextView)findViewById(R.id.set);
        client = Nearby.getConnectionsClient(this);
    }
    public void find(View view)
    {
        startAdvertising();
        startDiscovery();
        result.setText(msg2.getText());
        sendbtn.setEnabled(false);
    }
    private void sendtext(String id)
    {
        client.sendPayload(id,Payload.fromBytes(id.getBytes()));
    }
    private void buttonstate(boolean connected)
    {
        sendbtn.setEnabled(true);
        sendbtn.setVisibility(connected ? View.GONE : View.VISIBLE);
    }
    public void SendMessage(String text)
    {
        client.sendPayload(New_id,Payload.fromBytes(text.getBytes()));
        SetSendButton(false);
    }
    private void SetSendButton(boolean enabled)
    {
        sendbtn.setEnabled(enabled);
    }
    public void wifion()
    {
        btn = (Button)findViewById(R.id.won);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager open = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                open.setWifiEnabled(true);
                Toast msg = Toast.makeText(getApplicationContext(),"Wifi Turned On",Toast.LENGTH_LONG);
                msg.show();
            }
        });
    }
    public void wifiof()
    {
        wificlose = (Button)findViewById(R.id.wof);
        wificlose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager close = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                close.setWifiEnabled(false);
                Toast msg = Toast.makeText(getApplicationContext(),"Wifi Turned OFF",Toast.LENGTH_LONG);
                msg.show();
            }
        });
    }
    public void setHotspoton(){
       hotspoton = (Button)findViewById(R.id.hon);
       hotspoton.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               WifiManager hot = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
               hot.setWifiEnabled(false);
               Hotspot.isApOn(MainActivity.this);
               Hotspot.configApState(MainActivity.this);
           }
       });
    }
    private void startDiscovery() {
        // Note: Discovery may fail. To keep this demo simple, we don't handle failures.
        client.startDiscovery(
                getPackageName(), endpointDiscoveryCallback, new DiscoveryOptions(STRATEGY));
    }

    /** Broadcasts our presence using Nearby Connections so other players can find us. */
    private void startAdvertising() {
        // Note: Advertising may fail. To keep this demo simple, we don't handle failures.
        client.startAdvertising(
                text, getPackageName(), connectionLifecycleCallback, new AdvertisingOptions(STRATEGY));
    }
}
