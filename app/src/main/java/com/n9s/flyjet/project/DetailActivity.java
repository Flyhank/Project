package com.n9s.flyjet.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.n9s.flyjet.project.data.Phone;

public class DetailActivity extends AppCompatActivity
{
    Phone p;
    TextView tv1, tv2, tv3;
    int id;  //(成員變數)給整個程式用
    boolean fastBack = false; //供Detail頁連跳兩頁回ListView頁用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        id = getIntent().getIntExtra("id", 0);
        //***若在此宣告int id... 則成為(區域變數),僅供此段class使用,而且區域變數會蓋掉(成員變數)
        //***P.S. 若在此改int id...(區域變數),會發生無法delete單筆資料之問題
        p = MainActivity.dao.getPhone(id);    //從MainActivity.dao內取出Student資料
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        id = getIntent().getIntExtra("id", 0);  //
    }

    @Override
    protected void onResume()   //從Edit 頁覆蓋之畫面離開後,畫面回到ListView,執行以下所有資料顯示
    {
        super.onResume();
        if(fastBack)    //要連續跳兩頁
        {
            finish();
        }
        p = MainActivity.dao.getPhone(id);    //叫出database中剛被update這組的值
        tv1.setText(String.valueOf(p.id));
        tv2.setText(p.name);
        tv3.setText(String.valueOf(p.tel));
    }

    public void clickBack(View v)
    {
        finish();
    }

/*    public void clickDelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this); //顯示刪除確認小視窗?
        builder.setTitle("刪除確認");
        builder.setMessage("確認要刪除本筆資料嗎?");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                MainActivity.dao.delete(id);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        builder.show();
    }
*/
    public void clickEdit(View v)
    {
        Intent it = new Intent(DetailActivity.this, EditActivity.class);
        it.putExtra("id", id);
        fastBack = true;    //供Detail頁連跳兩頁回ListView頁用
        startActivity(it);
    }
}
