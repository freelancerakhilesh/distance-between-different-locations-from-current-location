package com.tbigeudnn.akhilesh.DistanceTo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tbigeudnn.akhilesh.myapplication.R;

import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends Activity implements LocationListener {

    TextView t1, t2, t3, t4;
    String provider,x,y;
    String[][] arr=new String[3][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.tv1);
        t2 = (TextView) findViewById(R.id.tv2);
        t3 = (TextView) findViewById(R.id.tv3);
        t4 = (TextView) findViewById(R.id.tv4);

        LocationManager loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        provider = loc.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           return;
        }
        Location location = loc.getLastKnownLocation(provider);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }loc.requestLocationUpdates(provider, 2000, 1, this);


        if (location != null) {
            onLocationChanged(location);
        }


    }


    @Override
    public void onLocationChanged(Location location) {



        double lat = location.getLatitude();
        double lng = location.getLongitude();
        x = Double.toString(lat);
        y = Double.toString(lng);


//GHANTAGHAR LOCATION
        Location locationB = new Location(provider);

            locationB.setLatitude(30.3243542);
            locationB.setLongitude(78.0415387);

            float distance = location.distanceTo(locationB);

            String a=Float.toString(distance);
            t1.setText(a);
            arr[0][0]="ghantaghar" ;
            arr[0][1]= a;

//ISBT LOCATION
        Location locationC = new Location(provider);

            locationC.setLatitude(30.1178154);
            locationC.setLongitude(76.8164819);

            distance = location.distanceTo(locationC);
        distance=distance/1000;
        distance=(float)Math.round(distance * 100) / 100;
        a=Float.toString(distance);
            arr[1][0]="isbt" ;
            arr[1][1]= a;

            t2.setText(a);

        //DALANWALA LOCATION
        Location locationD = new Location(provider);

            locationD.setLatitude(30.325030);
            locationD.setLongitude(78.053127);

            distance = location.distanceTo(locationD);
        a=Float.toString(distance);
            arr[2][0]="dalanwala" ;
            arr[2][1]= a;
            t3.setText(a);
            sort_loc();
            t4.setText(arr[2][0]);

    }

    public String sort_loc() {
        Arrays.sort(arr, new Comparator<String[]>() {

            @Override
            public int compare(final String[] first, final String[] second) {
                // here you should usually check that first and second
                // a) are not null and b) have at least two items
                // updated after comments: comparing Double, not Strings
                // makes more sense, thanks Bart Kiers
                return Double.valueOf(second[1]).compareTo(
                        Double.valueOf(first[1])
                );
            }
        });
        //System.out.println(Arrays.deepToString(arr));
        return arr[0][0];
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
