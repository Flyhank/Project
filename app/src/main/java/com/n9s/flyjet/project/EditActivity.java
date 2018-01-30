package com.n9s.flyjet.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.n9s.flyjet.project.data.Phone;

public class EditActivity extends AppCompatActivity
{
    Phone p;
    TextView tv4;
    EditText ed4, ed5;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = getIntent().getIntExtra("id", 0);
        p = MainActivity.dao.getPhone(id);    //從MainActivity.dao內取出Phone資料

        tv4 = findViewById(R.id.textView4);
        ed4 = findViewById(R.id.editText4);
        ed5 = findViewById(R.id.editText5);

        tv4.setText(String.valueOf(p.id));
        ed4.setText(p.name);
        ed5.setText(p.tel);
    }


    public void clickBack(View v)
    {
        finish();
    }

    public void clickUpdate(View v)
    {
        Phone p = new Phone(id, ed4.getText().toString(), ed5.getText().toString());

/*            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
            builder.setTitle("更新確認");
            builder.setMessage("確認要更新此筆資料嗎?");
            builder.setPositiveButton("確認", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    MainActivity.dao.update(s); //單筆完整Student資料放入加入MainActivity.dao內
                    finish();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                }
            });
            builder.show();
*/

        MainActivity.dao.update(p);
        finish();   //完成, 僅回到上一頁
    }
}
