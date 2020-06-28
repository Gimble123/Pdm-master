package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class Aula9Map extends AppCompatActivity {

    MapView map;

    ArrayList<String> listaLat;
    ArrayList<String> listaLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula9_map);

        Intent intent = getIntent();
        listaLat = intent.getStringArrayListExtra("lat");
        listaLon = intent.getStringArrayListExtra("lon");

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        map = (MapView) findViewById(R.id.map);
        map.getTileProvider().clearTileCache();
        Configuration.getInstance().setCacheMapTileCount((short) 12);
        Configuration.getInstance().setCacheMapTileOvershoot((short) 12);
        // Create a custom tile source
        map.setTileSource(new OnlineTileSourceBase("", 1, 20, 512, ".png",
                new String[]{"https://a.tile.openstreetmap.org/"}) {
            @Override
            public String getTileURLString(long pMapTileIndex) {
                return getBaseUrl()
                        + MapTileIndex.getZoom(pMapTileIndex)
                        + "/" + MapTileIndex.getX(pMapTileIndex)
                        + "/" + MapTileIndex.getY(pMapTileIndex)
                        + mImageFilenameEnding;
            }
        });

        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        GeoPoint startPoint;
        startPoint = new GeoPoint(0.0, 0.0);
        mapController.setZoom(11.0);
        mapController.setCenter(startPoint);

        final Context context = this;
        map.invalidate();

        createmarker();
    }

    public void createmarker() {
        if (map == null) {
            return;
        }

        for (int i = 0; i < listaLat.size(); i++) {
            GeoPoint gp = new GeoPoint(Double.parseDouble(listaLat.get(i)), Double.parseDouble(listaLon.get(i)));
            Marker my_marker = new Marker(map);
            my_marker.setPosition(gp);
            my_marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            my_marker.setTitle(String.valueOf(i));
            my_marker.setPanToView(true);
            map.getOverlays().add(my_marker);

        }

        map.invalidate();
    }
}
