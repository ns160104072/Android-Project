package com.junaeid.studentattendance;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static Button btn,show_A,clear_btn;
    TextView picture;
    DatabaseHandler myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickbutton();
        onClickbutton2();
        show_A = (Button)findViewById(R.id.show);
        clear_btn = (Button)findViewById(R.id.clear);
        picture = (TextView)findViewById(R.id.pic);
        picture.setMovementMethod(new ScrollingMovementMethod());
        final DatabaseHandler db = new DatabaseHandler(this);
        show_A.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> attendanceList=db.getAllAttendance();
                String result = "";
                for(Student attendance : attendanceList)
                {
                    result +="Id: "+ attendance.getId()+" Roll: "+attendance.getRoll();
                    result +="\n";
                    Log.d("Result",result);
                }
                if(attendanceList.size()  == 0)
                {
                    result = "No contact to display.";
                }
                picture.setText(result);
            }
        });
        clear_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Cleared All",Toast.LENGTH_LONG);
                db.ClearAttendance();
                List<Student> attendanceList=db.getAllAttendance();
                String result = "";
                for(Student attendance : attendanceList)
                {
                    result +="Id: "+ attendance.getId()+" Roll: "+attendance.getRoll();
                    result +="\n";
                    Log.d("Result",result);
                }
                if(attendanceList.size()  == 0)
                {
                    result = "No contact to display.";
                }
                picture.setText(result);
            }
        });
    }
    public void onClickbutton()
    {
        btn = (Button)findViewById(R.id.take);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.junaeid.studentattendance.Second");
                startActivity(intent);
            }
        });
    }
    public void onClickbutton2()
    {
        btn = (Button)findViewById(R.id.give);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager open = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                open.setWifiEnabled(true);
                Toast msg = Toast.makeText(getApplicationContext(),"Wifi Turned On",Toast.LENGTH_LONG);
                msg.show();
                Intent intent = new Intent("com.junaeid.studentattendance.Fourth");
                startActivity(intent);
            }
        });
    }
}
