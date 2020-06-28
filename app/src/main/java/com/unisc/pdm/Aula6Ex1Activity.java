package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aula6Ex1Activity extends AppCompatActivity {

    String[] clubes = {"Palmeiras", "Flamengo", "Atlético Mineiro", "Corinthians", "Santos", "Grêmio", "Ponte Preta", "Fluminense", "Atlético Paranaense", "Chapecoense", "Botafogo", "São Paulo", "Sport", "Cruzeiro", "Vitória", "Coritiba", "Internacional", "Figueirense", "Santa Cruz", "América Mineiro"};
    int[] pontos = {43, 40, 39, 37, 36, 36, 34, 34, 33, 30, 29, 28, 27, 26, 26, 26, 24, 24, 19, 13};
    int[] img = {R.drawable.pal, R.drawable.fla, R.drawable.cam, R.drawable.cor, R.drawable.san, R.drawable.gre, R.drawable.pon, R.drawable.flu, R.drawable.cap, R.drawable.cha, R.drawable.bot, R.drawable.sao, R.drawable.spt, R.drawable.cru, R.drawable.vit, R.drawable.cfc, R.drawable.inter, R.drawable.fig, R.drawable.sta, R.drawable.ame};

    private ListView listView;

    String [] de = {"pos", "logo", "clube", "pontos"};
    int [] para = {R.id.posTime, R.id.imgTime, R.id.nomeTime, R.id.ptsTime};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula6_ex1);

        listView = findViewById(R.id.listView);

        List<Map<String, Object>> lista = new ArrayList<>();
        for (int i=0; i < clubes.length; i++) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put(de[0], String.valueOf(i + 1));
            mapa.put(de[1], img[i]);
            mapa.put(de[2], clubes[i]);
            mapa.put(de[3], pontos[i] + " pts");
            lista.add(mapa);
        }

        SimpleAdapter adapter = new TimesAdaptador(this, lista, R.layout.uma_linha, de, para );
        listView.setAdapter(adapter);
    }

    private class TimesAdaptador extends SimpleAdapter {

        public TimesAdaptador(Context ctx, List<Map<String, Object>> lista, int uma_linha, String[] de, int[] para) {
            super(ctx, lista, uma_linha, de, para);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view =  super.getView(position, convertView, parent);

            TextView pos = view.findViewById(R.id.posTime);
            TextView nome = view.findViewById(R.id.nomeTime);
            TextView pts = view.findViewById(R.id.ptsTime);

            if (position % 2 == 0 ) {
                view.setBackgroundColor(Color.YELLOW);
                pos.setTextColor(Color.BLACK);
                nome.setTextColor(Color.BLACK);
                pts.setTextColor(Color.BLACK);
            }
            else {
                view.setBackgroundColor(Color.parseColor("#007F00"));
                pos.setTextColor(Color.WHITE);
                nome.setTextColor(Color.WHITE);
                pts.setTextColor(Color.WHITE);
            }

            return view;
        }
    }
}
