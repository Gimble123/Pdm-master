package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Aula5ListActivity extends ListActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula5_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listarViagens());
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(this);

    }

    private List<String> listarViagens() {
        String [] array = {"São Paulo","Bonito","Maceió"};
        return Arrays.asList(array);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        TextView textView = (TextView) view;
        String conteudo = "Viagem selecionada " +  textView.getText().toString();
        Toast.makeText(getApplicationContext(), conteudo, Toast.LENGTH_SHORT).show();
    }
}
