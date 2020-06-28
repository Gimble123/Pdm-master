package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Aula71Activity extends AppCompatActivity {

    private TextView txtUmidade,txtTemperatura, txtPressao, txtOrvalho;
    private ListView listView;
    String de [] = {"temp","umid", "orv", "press"};
    int para [] = {R.id.txtTemp,R.id.txtUmid,R.id.txtOrv, R.id.txtPress };
    List<Map<String, String >> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula71);

        txtTemperatura = findViewById(R.id.txtTemperatura);
        txtUmidade =  findViewById(R.id.txtUmidade);
        txtPressao =  findViewById(R.id.txtPressao);
        txtOrvalho =  findViewById(R.id.txtOrvalho);
        listView =  findViewById(R.id.listView);
    }

    public void onClickBuscar(View view) {
        lista = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://ghelfer.net/la/weather.json", new AsyncHttpResponseHandler() {

             @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String data = new String(response);
                 //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                 try {
                     loadData(data);
                 } catch (JSONException e) {
                     Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                 }
             }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData(String data) throws JSONException {
        double sumTemp = 0;
        double sumUmid = 0;
        double sumPres = 0;
        double sumOrv = 0;

        JSONObject res = new JSONObject(data);
        JSONArray array = res.getJSONArray("weather");
        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.getJSONObject(i);
            String temp = json.get("temperature").toString();
            sumTemp += Double.parseDouble(temp);
            String umid = json.get("humidity").toString();
            sumUmid += Double.parseDouble(umid);
            String po = json.get("dewpoint").toString();
            sumOrv += Double.parseDouble(po);
            String press = json.get("pressure").toString();
            sumPres += Double.parseDouble(press);

            Map<String,String> mapa = new HashMap<>();
            mapa.put("temp", temp);
            mapa.put("umid", umid);
            mapa.put("orv",  po);
            mapa.put("press", press);
            lista.add(mapa);
        }

        txtTemperatura.setText(String.valueOf(sumTemp / array.length()));
        txtUmidade.setText(String.valueOf(sumUmid / array.length()));
        txtPressao.setText(String.valueOf(sumPres / array.length()));
        txtOrvalho.setText(String.valueOf(sumOrv/ array.length()));

        SimpleAdapter adapter = new SimpleAdapter(this,lista,R.layout.linha_tempo,de,para);
        listView.setAdapter(adapter);
    }
}
