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
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.n9s.flyjet.project.MainActivity.dao;

public class NavigationActivity extends AppCompatActivity
{
    MyListener listener;
    LocationManager lm;
    Address addr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        listener = new MyListener();
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    public void clickNavg2(View v)
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
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
    }

    class MyListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            Geocoder geocoder = new Geocoder(NavigationActivity.this);
            try
            {
                List<Address> mylist2 = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                lm.removeUpdates(listener);
                addr = mylist2.get(0);
                Log.d("LOC", addr.getAddressLine(0));

                //Uri uri = Uri.parse("https://www.google.com.tw/maps/dir/220台灣新北市莊敬路8號/220新北市板橋區文化路二段522號");
                Uri uri = Uri.parse("https://www.google.com.tw/maps/dir/" + addr.getAddressLine(0) + "/220新北市板橋區文化路二段522號");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
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
