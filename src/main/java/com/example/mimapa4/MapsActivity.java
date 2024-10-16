package com.example.mimapa4;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mimapa4.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mmap;
    private ActivityMapsBinding binding;
    private LocationManager lm;
    private Marker marcador;
    double lat=0.0, lng=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_maps);

        int status= GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status== ConnectionResult.SUCCESS){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else {
             Dialog d=GooglePlayServicesUtil.getErrorDialog(status,(Activity) getApplicationContext(), 10);
             d.show();
        }

    }
/*
    public void encendidogps(){
        lm=(LocationManager) getSystemService(LOCATION_SERVICE);


        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            Toast.makeText(this, "GPS IS ON", Toast.LENGTH_LONG).show();
            //ubicaciones();
        }else {
            Toast.makeText(this, "GPS IS OFF", Toast.LENGTH_LONG).show();
        }
    }
*/

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mmap = googleMap;
        lm=(LocationManager) getSystemService(LOCATION_SERVICE);
        float zlevel=20;

        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            Toast.makeText(this, "GPS IS ON", Toast.LENGTH_LONG).show();
            UiSettings uiSettings=mmap.getUiSettings();
            uiSettings.setZoomControlsEnabled(true);
            LatLng lugar=new LatLng(-2.1931478,-79.8802269);
            CameraUpdate ubico=CameraUpdateFactory.newLatLngZoom(lugar, zlevel);
            if (marcador!=null)marcador.remove();
            marcador=mmap.addMarker(new MarkerOptions().position(lugar).title("Islas Galapagos")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mmap.animateCamera(ubico);


            mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(lugar, zlevel));


        }else {
            Toast.makeText(this, "GPS IS OFF", Toast.LENGTH_LONG).show();
        }






    }

    private void agregarmarca(double lat, double lng){
        try{
            LatLng coordenadas=new LatLng(lat,lng);
            float zlevel=20;
            UiSettings ui=mmap.getUiSettings();
            ui.setZoomControlsEnabled(true);
            CameraUpdate ubico=CameraUpdateFactory.newLatLngZoom(coordenadas,zlevel);
            if (marcador!=null)marcador.remove();
            marcador=mmap.addMarker(new MarkerOptions()
                    .position(coordenadas)
                    .title("Islas Galapagos")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mmap.animateCamera(ubico);
            mmap.moveCamera(CameraUpdateFactory.newLatLng(coordenadas));
        }catch (Exception e){
            Toast.makeText(this, " " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void actualesubicaciones(Location location){
        if (location!=null){
            lat=location.getLatitude();
            lng=location.getLongitude();
            agregarmarca(lat,lng);
        }
    }

    LocationListener locationListener=new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            actualesubicaciones(location);

        }
    };

    public void ubicaciones(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_DENIED){
            //Uri ninicial=Uri.parse("-2.1958174,-79.9004907");
            //Intent minicio=new Intent(Intent.ACTION_VIEW, ninicial);


            return ;

        }

        LocationManager locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualesubicaciones(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,20000,0,locationListener);

    }

}