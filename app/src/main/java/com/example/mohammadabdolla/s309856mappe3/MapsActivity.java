package com.example.mohammadabdolla.s309856mappe3;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mohammadabdolla.s309856mappe3.model.Rom;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    List<Rom> rooms;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        rooms = new ArrayList<>();
        MapAsyncTask task = new MapAsyncTask();
        try {
            task.execute("http://student.cs.hioa.no/~s309856/jsonout.php").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rooms = task.getRooms();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (Rom r : rooms) {
            Log.i("ROOM", r.getRomNr() + r.getLatitude() + r.getLongitude());
            double latitude = Double.parseDouble(r.getLatitude());
            double longitude = Double.parseDouble(r.getLongitude());
            LatLng latLng = new LatLng(latitude, longitude);
            float zoomSize = 15.0f;
            mMap.addMarker(new MarkerOptions().position(latLng).title(r.getRomNr()).snippet(""));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomSize));
        }
    }

    public void leggTilBestilling(View view) {
        Intent intent = new Intent(this, BestillActivity.class);
        startActivity(intent);
    }

    public void visBestillinger(View view) {
        Intent intent = new Intent(this, ReservasjonerActivity.class);
        startActivity(intent);
    }

    public void leggTilRom(View view) {
        Intent intent = new Intent(this, LeggTilRomActivity.class);
        startActivity(intent);
    }
}
