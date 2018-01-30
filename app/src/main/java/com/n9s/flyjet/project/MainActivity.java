package com.n9s.flyjet.project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.n9s.flyjet.project.data.Phone;
import com.n9s.flyjet.project.data.PhoneFileDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    public static PhoneFileDAO dao;
    //ListView lv;
    //DBType dbType;  //enum宣告

    ArrayList<String> phoneNames;   //陣列
    //ArrayAdapter<String> adapter;   //工具
    //Context context;
    //Phone p[];
    //String id;
    //String name;
    //String tel;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new PhoneFileDAO(this);
        if(dao.getList().size()==0)
        {
            dao.add(new Phone(1, "聯絡人名字", "0000000000"));
            dao.add(new Phone(2, "聯絡人名字", "0000000000"));
            dao.add(new Phone(3, "聯絡人名字", "0000000000"));
            dao.add(new Phone(4, "聯絡人名字", "0000000000"));
            dao.add(new Phone(5, "聯絡人名字", "0000000000"));
            dao.add(new Phone(6, "聯絡人名字", "0000000000"));
        }

    }

    public void clickContact(View v)
    {
        Intent it = new Intent(MainActivity.this, ListActivity.class);
        startActivity(it);
    }

    public void clickSetting(View v)
    {
        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
    }


}

