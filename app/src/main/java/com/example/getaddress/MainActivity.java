package com.example.getaddress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;

public class MainActivity extends AppCompatActivity {

    Double latitube = 0.0;
    Double longitude =0.0;
    static String TAG = "MainActivity";
    Location gps_loc = null,network_loc = null,final_loc =null;
    TextView locationtxt;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationtxt = (TextView)findViewById(R.id.locationtxt);

        requestPermission();
        checkPermission();

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_NETWORK_STATE)!= PackageManager.PERMISSION_GRANTED)
        {

            Toast.makeText(this,"not granted",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
        try {
            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc  =locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (gps_loc !=null){
            final_loc=gps_loc;
            latitube = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }else if (network_loc !=null){
            final_loc = network_loc;
            latitube = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }else {
            longitude =0.0;
            latitube = 0.0;
        }
        try {
            Geocoder geocoder =new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses =geocoder.getFromLocation(latitube,longitude,1);
            if (addresses!=null && addresses.size()>0){
                String address = addresses.get(0).getAddressLine(0);
                String city  = addresses.get(0).getLocality();
                String state =addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postal_code  =addresses.get(0).getPostalCode();
                String knownName= addresses.get(0).getFeatureName();
                locationtxt.setText("Address:  "+address+"\n\n"+
                        "City:  "+city+"\n\n"+
                        "state:  "+state+"\n\n"+
                        "country:  "+country+"\n\n"+
                        "postal_code:  "+postal_code+"\n\n"+
                        "knownName:  "+knownName+"\n\n");
            }
        }catch (Exception e){

        }
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_NETWORK_STATE);




        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED &&result2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION,ACCESS_NETWORK_STATE}, PERMISSION_REQUEST_CODE);

    }
}
