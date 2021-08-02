package com.junaeid.studentattendance;

/**
 * Created by msi on 1/27/2018.
 */

public class Student {
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }
    public void setRoll(String roll) {
        this.roll = roll;
    }
    int id;
    String roll;
    String name;
    public Student()
    {

    }
    public Student(String r)
    {
        roll = r;
    }
    public Student(int i,String r)
    {
        id = i;
        roll = r;
    }
}
