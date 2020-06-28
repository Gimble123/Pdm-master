package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Aula9GpsActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    private TextView txtLat, txtLon, txtStatus;
    ListView listView;
    List<String> lista;
    ArrayList<String> listaLat = new ArrayList<>();
    ArrayList<String> listaLon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula9_gps);

        txtLat = findViewById(R.id.txtLat);
        txtLon = findViewById(R.id.txtLon);
        txtStatus = findViewById(R.id.txtStatus);
        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Aula9Map.class);
                intent.putStringArrayListExtra("lat",listaLat );
                intent.putStringArrayListExtra("lon",listaLon );
                startActivity(intent);
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        long tempo = 0;
        float distancia = 10;
        lista = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this,"Favor permitir uso da localização nas Configurações do dispositivo", Toast.LENGTH_LONG).show();

            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, tempo, distancia, this);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,tempo, distancia, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat.setText("Latitude = " + location.getLatitude());
        txtLon.setText("Longitude = " + location.getLongitude());
        txtStatus.setText("Provider = " + location.getProvider());

        String str = "Latitude = " + location.getLatitude() + " Longitude = " + location.getLongitude();
        lista.add(str);
        listaLat.add(String.valueOf(location.getLatitude()));
        listaLon.add(String.valueOf(location.getLongitude()));

        ArrayAdapter adapter =  new ArrayAdapter(this,android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
