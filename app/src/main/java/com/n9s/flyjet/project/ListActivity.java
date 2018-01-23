package com.n9s.flyjet.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.n9s.flyjet.project.data.Phone;
import com.n9s.flyjet.project.data.PhoneFileDAO;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity
{
    public static PhoneFileDAO dao;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dao = new PhoneFileDAO(this);
    }

    @Override
    protected void onResume()   //新增資料時,新頁面會蓋住listView頁面進入stop and hale; 新增資料回來後,
    //listView頁面會resume
    {
        super.onResume();
        lv = (ListView) findViewById(R.id.listView);
        ArrayList<String> phoneNames = new ArrayList<>();

        for (Phone s : dao.getList()) //getList:整個資料庫資料
        {
            phoneNames.add(s.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this,
                android.R.layout.simple_list_item_1, phoneNames);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)   //i是position
            //******點擊任一筆資料時根據ViewList上的位置,而非任何資料 *********
            {
                Intent it = new Intent(ListActivity.this, DetailActivity.class);
                it.putExtra("id", dao.getList().get(i).id);     //取出位置內的id值
                startActivity(it);
            }
        });
    }

    public void clickBack1(View v)
    {
        finish();
    }

    public void clickclickAdd1(View v)
    {
        Intent it = new Intent(ListActivity.this, AddActivity.class);
        startActivity(it);
    }
}
