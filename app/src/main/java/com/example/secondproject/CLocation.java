package com.example.secondproject;

import android.location.Location;


public class CLocation extends Location
{
    private static final float km = 3.6f;

    public CLocation(Location location)
    {
        this(location,true);
    }

    public CLocation(Location location,boolean bUserMetricUnits)
    {
        super(location);
    }


    @Override
    public float distanceTo(Location dest)
    {
        float nDistance = super.distanceTo(dest);

        return nDistance;
    }

    @Override
    public double getAltitude()
    {
        double nAltitude = super.getAltitude();
        return nAltitude;
    }


    @Override
    public float getSpeed()
    {
        float nSpeed = super.getSpeed()* km;
        return nSpeed;
    }

    @Override
    public float getAccuracy()
    {
        float nAccuracy = super.getAccuracy();
        return nAccuracy;
    }

    @Override
    public double getLatitude()
    {
        return super.getLatitude();
    }

    @Override
    public double getLongitude()
    {
        return super.getLongitude();
    }
}
