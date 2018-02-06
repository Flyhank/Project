package com.n9s.flyjet.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
{
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ed = (EditText) findViewById(R.id.editText);
    }

    public void clickChange(View v)
    {
        SharedPreferences sp = getSharedPreferences("myhome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("data1", ed.getText().toString());
        editor.commit();

        Toast.makeText(HomeActivity.this, "儲存完畢!", Toast.LENGTH_SHORT).show();

        //Intent it = new Intent (HomeActivity.this, Main2Activity.class); //不需將儲存後地址傳到Main2Activity
        //it.putExtra("data1", ed.getText().toString());
        //startActivity(it);
        //新北市板橋區莊敬路9號
    }
    public void clickCheck(View v)
    {
        SharedPreferences sp = getSharedPreferences("myhome", MODE_PRIVATE);
        String str = sp.getString("data1", "");
        //TextView tv = (TextView) findViewById(R.id.textView);
        ed.setText(str);
    }

    public void clickBack(View v)
    {
        finish();
    }
}
