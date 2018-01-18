package com.n9s.flyjet.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.n9s.flyjet.project.data.Phone;
import com.n9s.flyjet.project.data.PhoneFileDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    public static PhoneFileDAO dao;
    //以(靜態物件)StudentScoreDAO class宣告變數dao
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         dao = new PhoneFileDAO(this);
    }

    public void clicksetting(View v)
    {
        Intent it = new Intent(MainActivity.this, ListActivity.class);
        startActivity(it);
    }





}

