package com.junaeid.studentattendance;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by msi on 1/26/2018.
 */

public class Fourth extends Activity{
    Button wificlose,attendancegive;
    EditText section,department,roll_no;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth);
        wifi_0ff();
        attendancegive = (Button)findViewById(R.id.btngive);
        section = (EditText)findViewById(R.id.sec2);
        department = (EditText)findViewById(R.id.dept2);
        roll_no = (EditText)findViewById(R.id.roll2);
        final DatabaseHandler db = new DatabaseHandler(this);
        attendancegive.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String section_name = section.getText().toString();
                String department_name = department.getText().toString();
                String rollNo = roll_no.getText().toString();
                if(section_name.equals("") || department_name.equals("") || rollNo.equals(""))
                {
                    Toast msg = Toast.makeText(getApplicationContext(),"Fill up Properly",Toast.LENGTH_LONG);
                    msg.show();
                }
                else
                {
                    Toast msg =Toast.makeText(getApplicationContext(),"Attendance Given",Toast.LENGTH_LONG);
                    msg.show();
                    db.addAttendance(new Student(rollNo));
                }
            }
        });
    }

    public void wifi_0ff()
    {
        wificlose = (Button)findViewById(R.id.btndone);
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
}
