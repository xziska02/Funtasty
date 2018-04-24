package sk.peterziska.funtasty.UI.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import sk.peterziska.funtasty.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private double latitude;
    private String name;
    private double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        name = getIntent().getStringExtra("name");
        latitude = Double.valueOf(getIntent().getStringExtra("latitude"));
        longitude = Double.valueOf(getIntent().getStringExtra("longitude"));

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(longitude, latitude);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title(name));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
