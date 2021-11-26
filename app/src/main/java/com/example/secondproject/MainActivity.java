package com.example.secondproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Formatter;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements LocationListener {
    TextView textViewSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewSpeed = findViewById(R.id.textViewSpeed);

        //check for gps permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
        }
        else
        {
            doStuff();
        }
    }

    @Override
    public void onLocationChanged(Location location)
    {
        if(location != null){
            CLocation myLocation = new CLocation(location);
            this.updateSpeed(myLocation);
            this.updateLocation(myLocation);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }

    @SuppressLint("MissingPermission")
    private void doStuff()
    {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if(locationManager != null)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
        }
        Toast.makeText(this,"Waiting GPS Connection!", Toast.LENGTH_SHORT).show();
    }

    private void updateSpeed(CLocation location)
    {
        float nCurrentSpeed =0;
        if(location!=null)
        {
            nCurrentSpeed = location.getSpeed();
        }

        Formatter fmt = new Formatter(new StringBuilder());
        fmt.format(Locale.US,"%5.1f",nCurrentSpeed);
        String strCurrentSpeed = fmt.toString();
        strCurrentSpeed =strCurrentSpeed.replace(" ","0");

        textViewSpeed.setText(strCurrentSpeed+" km/h");
    }

    private void updateLocation(CLocation location){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                this.doStuff();
            }
            else
            {
                finish();
            }
        }
    }
}
