package com.junaeid.studentattendance;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="StudentAttendance";
    private static final String TABLE_NAME="Attendance";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_Roll="roll";

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Log.v("db created", "yes");
        //String CREATE_CONTACT_TABLE="CREATE TABLE "+ TABLE_NAME +"("
          //      + KEY_ID +" INTEGER PRIMARY KEY,"
            //    + KEY_NAME +" TEXT,"
              //  + KEY_CONTACTNO +" TEXT" +")";

        String sql= "CREATE TABLE Attendance (ID INTEGER PRIMARY KEY" +
                ", Roll TEXT)";
        db.execSQL(sql);
        //Log.v("db created", "yes");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addAttendance(Student student)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO Attendance (Roll)" +
                "VALUES('"+student.getRoll()+"')";

        //String qry = "INSERT INTO CONTACT(NAME,PHONENO)VALUES('XYZ','016')";
        db.execSQL(query);

        //ContentValues value=new ContentValues();
        //value.put(KEY_NAME, contact.getName());
        //value.put(KEY_CONTACTNO,contact.getContactNumber());

        //db.insert(TABLE_NAME, null,value);

        db.close();

    }

    public List<Student> getAllAttendance()
    {
        List<Student> attendanceList=new ArrayList<Student>();

        String selectquery="SELECT * FROM "+ TABLE_NAME;// where phoneno LIKE '017%'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                Student student= new Student(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1));
                attendanceList.add(student);
            }while(cursor.moveToNext());
        }

        return attendanceList;
    }
    public void ClearAttendance()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //String query = "DELETE * From "+TABLE_NAME;
        //db.execSQL(query);
        db.execSQL("delete from "+ TABLE_NAME);

        //db.delete(TABLE_NAME, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }

}
