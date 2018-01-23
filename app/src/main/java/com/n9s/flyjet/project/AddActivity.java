package com.n9s.flyjet.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.n9s.flyjet.project.data.Phone;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void clickBack1(View v)
    {
        finish();
    }

    public void clickAdd(View v)    //按下Add鍵後開始執行抓取資料
    {
        EditText ed3 = (EditText) findViewById(R.id.editText3);
        EditText ed4 = (EditText) findViewById(R.id.editText4);
        EditText ed5 = (EditText) findViewById(R.id.editText5);
        int id = Integer.valueOf(ed3.getText().toString());     //ed1: int(id); String->int
        String name = ed4.getText().toString();             //ed2: String(name)
        String tel = ed5.getText().toString();
        //int score = Integer.valueOf(ed3.getText().toString());  //ed3:number(score)
        MainActivity.dao.add(new Phone(id, name, tel)); //單筆完整Student資料放入加入MainActivity.dao內
        //finish();
        Intent it = new Intent(this, ListActivity.class);
        startActivity(it);


    }
}
