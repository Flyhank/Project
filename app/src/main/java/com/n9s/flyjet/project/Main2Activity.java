package com.n9s.flyjet.project;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.provider.CalendarContract.CalendarCache.URI;
import static com.n9s.flyjet.project.MainActivity.dao;

public class Main2Activity extends AppCompatActivity {
    LocationManager lm;
    Address addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    public void clickPic(View v) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 11);
    }

    public void clickCam(View v) {
        //Intent i = new Intent(Intent.ACTION_CAMERA_BUTTON, null);     //相機noise出現
        //this.sendBroadcast(i);
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, 123);
    }

    public void clickRecord(View v) {
        Intent mi = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivity(mi);
    }

    public void clickFB(View v) {
        Uri uri = Uri.parse("http://www.facebook.com");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }



/*    public void clickLine(View v)
    {
        private class webViewClient extends WebViewClient
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                if (url.contains("line.naver.jp"))
                {
                    Intent iuri = null;
                    try
                    {
                        iuri = Intent.parseUri(url, 0);
                    } catch (URISyntaxException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    startActivity(iuri);
                }
                return true;
            }
        }
    }
    */

    public void clickPhone(View v)
    {
        finish();
    }

    //=====================================================================================================================================
    public void clickSOS2(View v)
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

    public void startLoc()
    {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new Main2Activity.MyListener());
    }

    class MyListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            Geocoder geocoder = new Geocoder(Main2Activity.this);
            try
            {
                List<Address> mylist2 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                addr = mylist2.get(0);
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

    public void clickNavg(View v)
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
        //Uri uri = Uri.parse("https://www.google.com.tw/maps/dir/220台灣新北市莊敬路8號/220新北市板橋區文化路二段522號");
        Uri uri = Uri.parse("https://www.google.com.tw/maps/dir/"+addr.getAddressLine(0)+"/220新北市板橋區文化路二段522號");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

}



