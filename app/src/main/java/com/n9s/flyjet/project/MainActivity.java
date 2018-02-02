package com.n9s.flyjet.project;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.n9s.flyjet.project.data.Phone;
import com.n9s.flyjet.project.data.PhoneFileDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity
{
    public static PhoneFileDAO dao;
    Button btn1, btn2, btn3, btn4, btn5, btn6;
    ArrayList<String> phoneNames;   //陣列
    //ArrayAdapter<String> adapter;   //工具
    LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

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

    @Override
    protected void onResume()   //新增資料時,新頁面會蓋住listView頁面進入stop and hale; 新增資料回來後,
    //listView頁面會resume
    {
        super.onResume();
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);

        btn1.setText(dao.getList().get(0).name.toString());
        btn2.setText(dao.getList().get(1).name.toString());
        btn3.setText(dao.getList().get(2).name.toString());
        btn4.setText(dao.getList().get(3).name.toString());
        btn5.setText(dao.getList().get(4).name.toString());
        btn6.setText(dao.getList().get(5).name.toString());

    }

    public void clickFone1(View v)
    {
        Uri uri = Uri.parse("tel:"+dao.getList().get(0).tel.toString()); //一定要有"tel:"才行
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);
    }
    public void clickFone2(View v)
    {
        Uri uri = Uri.parse("tel:"+dao.getList().get(1).tel.toString()); //一定要有"tel:"才行
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);
    }
    public void clickFone3(View v)
    {
        Uri uri = Uri.parse("tel:"+dao.getList().get(2).tel.toString()); //一定要有"tel:"才行
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);
    }
    public void clickFone4(View v)
    {
        Uri uri = Uri.parse("tel:"+dao.getList().get(3).tel.toString()); //一定要有"tel:"才行
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);
    }
    public void clickFone5(View v)
    {
        Uri uri = Uri.parse("tel:"+dao.getList().get(4).tel.toString()); //一定要有"tel:"才行
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);
    }
    public void clickFone6(View v)
    {
        Uri uri = Uri.parse("tel:"+dao.getList().get(5).tel.toString()); //一定要有"tel:"才行
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(it);
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

    //=====================================================================================================================================
    public void clickSOS(View v)
    {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 321);
            return;
        }
        else
        {
            startLoc();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 321)
        {
            if (grantResults.length>0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                startLoc();
            }
            else
            {
            }
        }
    }

    private void startLoc()
    {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new MyListener());
    }

    class MyListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            Geocoder geocoder = new Geocoder(MainActivity.this);
            try
            {
                List<Address> mylist2 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                Address addr = mylist2.get(0);
                Log.d("LOC", addr.getAddressLine(0));

                Intent smsIntent = new Intent(android.content.Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", dao.getList().get(0).tel.toString()+";"+dao.getList().get(1).tel.toString());
                smsIntent.putExtra("sms_body", "我需要幫忙!! 位置在: "+addr.getAddressLine(0)+"; (緯度: "+location.getLatitude()+"; "+"經度: "+location.getLongitude()+"). "+", 請快點過來幫我!!!");
                startActivity(smsIntent);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle)
        {

        }
        @Override
        public void onProviderEnabled(String s)
        {

        }
        @Override
        public void onProviderDisabled(String s)
        {

        }

    }


}

