package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class Aula5Ex1ctivity extends ListActivity {

    String [] nomes = {"Lamb Ari", "Beto Neira", "Brita Deira", "Gil Lete", "Astolfo"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula5_ex1ctivity);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                Arrays.asList(nomes));
        ListView listView = getListView();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selecao = nomes[i];
                Intent intent = new Intent(getApplicationContext(), Aula5Ex2ctivity.class);
                intent.putExtra("nome", selecao);
                startActivity(intent);
            }
        });
    }
}
