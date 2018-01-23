package com.n9s.flyjet.project;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.n9s.flyjet.project.data.Phone;
import com.n9s.flyjet.project.data.PhoneFileDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    public static PhoneFileDAO dao;
    //以(靜態物件)StudentScoreDAO class宣告變數dao
    ListView lv;
    ArrayList<String> phoneNames;
    ArrayAdapter<String> adapter;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Phone[] s={
                new Phone(1, "聯絡人名字", "0000000000"),
                new Phone(2, "聯絡人名字", "0000000000"),
                new Phone(3, "聯絡人名字", "0000000000"),
                new Phone(4, "聯絡人名字", "0000000000"),
                new Phone(5, "聯絡人名字", "0000000000"),
                new Phone(6, "聯絡人名字", "0000000000")};
        saveFile();


        phoneNames = new ArrayList<>();
         dao = new PhoneFileDAO(this);
    }

    private void saveFile()
    {
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void clickContact(View v)
    {
        Intent it = new Intent(MainActivity.this, ListActivity.class);
        startActivity(it);
    }





}

