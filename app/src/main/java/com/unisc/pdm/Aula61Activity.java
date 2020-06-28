package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aula61Activity extends ListActivity {

    String[] de = {"imagem", "destino","data", "total"};
    int[] para = {R.id.imgView, R.id.txtDest, R.id.txtData, R.id.txtTotal};
    List<Map<String, Object>> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula61);

        lista = new ArrayList<>();

        int[] tipo = {R.drawable.ic_world, R.drawable.ic_work, R.drawable.ic_business, R.drawable.ic_home};
        String[] cidades = {"São Paulo", "Porto Alegre", "Maceió", "Curitiba"};
        String[] periodos = {"01/Ago a 31/Ago", "01/Set a 30/Set", "01/Out a 31/Out", "01/Nov a 30/Nov",};
        String[] despesas = {"Total R$ 2.000,00", "Total R$ 3.000,00", "Total R$ 4.000,00", "Total R$ 5.000,00",};

        for (int i = 0; i < tipo.length; i++) {
            Map<String,Object> mapa = new HashMap<>();
            mapa.put("imagem", tipo[i]);
            mapa.put("destino", cidades[i]);
            mapa.put("data", periodos[i]);
            mapa.put("total", despesas[i]);
            lista.add(mapa);
        }


        SimpleAdapter adapter = new MeuAdaptador(this, lista, R.layout.line_item, de, para);
        ListView listView = getListView();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, Object> selecao = lista.get(i);
                String destino = selecao.get("destino").toString();
                String periodo = selecao.get("data").toString();
                String valores = selecao.get("total").toString();
                Toast.makeText(getApplicationContext(),destino + "\n"
                        + periodo + "\n"
                        + valores, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
