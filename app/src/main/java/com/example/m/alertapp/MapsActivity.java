package com.example.m.alertapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    Geocoder gcd;
    String ciudad;

    String placa = "apk234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);


    }
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        //Toast.makeText(this, "lat: " + location.getLatitude() + ", lon: " + location.getLongitude(), Toast.LENGTH_LONG).show();
        float lat = (float) location.getLatitude();
        float lng = (float) location.getLongitude();
        gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = gcd.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size() > 0) {

            ciudad = addresses.get(0).getLocality();
            Toast.makeText(this, ciudad +","+getDay(), Toast.LENGTH_SHORT).show();


        }
        else {
            // do your stuff
        }
    }


    public String getDay(){
        String Valor_dia = null;
        int diaSemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if (diaSemana == 1) {
            Valor_dia = "Domingo";
        } else if (diaSemana == 2) {
            Valor_dia = "Lunes";
        } else if (diaSemana == 3) {
            Valor_dia = "Martes";
        } else if (diaSemana == 4) {
            Valor_dia = "Miercoles";
        } else if (diaSemana == 5) {
            Valor_dia = "Jueves";
        } else if (diaSemana == 6) {
            Valor_dia = "Viernes";
        } else if (diaSemana == 7) {
            Valor_dia = "Sabado";
        }
        return Valor_dia;
        
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;

    }
}
