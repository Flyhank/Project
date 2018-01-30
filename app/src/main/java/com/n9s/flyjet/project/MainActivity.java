package com.n9s.flyjet.project;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.n9s.flyjet.project.data.Phone;
import com.n9s.flyjet.project.data.PhoneFileDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    public static PhoneFileDAO dao;
    //ListView lv;
    //DBType dbType;  //enum宣告
    Button btn1, btn2, btn3, btn4, btn5, btn6;

    ArrayList<String> phoneNames;   //陣列
    //ArrayAdapter<String> adapter;   //工具



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);

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

        btn1.setText(dao.getList().get(0).name.toString());
        btn2.setText(dao.getList().get(1).name.toString());
        btn3.setText(dao.getList().get(2).name.toString());
        btn4.setText(dao.getList().get(3).name.toString());
        btn5.setText(dao.getList().get(4).name.toString());
        btn6.setText(dao.getList().get(5).name.toString());

    }

    public void clickContact(View v)
    {
        Intent it = new Intent(MainActivity.this, ListActivity.class);
        startActivity(it);
    }

    public void clickFun(View v)
    {
        Intent it = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(it);
    }

    public void clickSetting(View v)
    {
        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
    }


}

