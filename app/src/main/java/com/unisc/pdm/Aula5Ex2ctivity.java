package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Aula5Ex2ctivity extends AppCompatActivity {

    private TextView txtNome;
    private Spinner spinnerUF;
    private ListView listViewCidade;

    String[] uf = {"Rio Grande do Sul","Santa Catarina","Paraná","São Paulo","Minas Gerais"};
    String [][] cidades = {
            new String []{"Porto Alegre","Santa Maria","Caxias do Sul","Santa Cruz do Sul","Pelotas","Rio Grande"},
            new String []{"Florianópolis","Joinville","Criciúma","Chapecó","Blumenau"},
            new String []{"Curitiba","Londrina","Maringá","Foz do Iguaçu"},
            new String []{"São Paulo","Campinas","Osasco"},
            new String []{"Belo Horizonte", "Uberaba"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula5_ex2ctivity);

        txtNome = findViewById(R.id.txtNome);
        spinnerUF = findViewById(R.id.spinnerUF);
        listViewCidade = findViewById(R.id.listViewCidade);
        listViewCidade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv = view.findViewById(android.R.id.text1);

                Toast.makeText(getApplicationContext(), tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        txtNome.setText(intent.getStringExtra("nome"));

        ArrayAdapter<CharSequence> adapterSpinner = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item, listaUFs());
        spinnerUF.setAdapter(adapterSpinner);
        spinnerUF.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                /*String [] selecao = getRow(position);
                ArrayAdapter<String> adapterListView =
                        new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, Arrays.asList(selecao));
                listViewCidade.setAdapter(adapterListView);
                 */

                String uf = (String) adapterView.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), uf, Toast.LENGTH_SHORT).show();

                //porem se eu usar aqui, a lista fica em branco
                ArrayAdapter<String> adapterCidades =
                       new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaCidades(uf));
                listViewCidade.setAdapter(adapterCidades);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {  }
        });

    }

    private String []  listaUFs(){
        return new String [] {"RS", "SC", "PR"};
    }


    private List<String> listaCidades(String uf){
        switch (uf){
            case "RS":
                return Arrays.asList("Venâncio Aires", "Santa Cruz", "Lajeado");
            case "SC":
                return Arrays.asList("Florianópolis", "Blumenau", "Tubarão");
            case "PR":
                return Arrays.asList("Curitiba", "Londrina", "Maringá");
            default:
                return Arrays.asList("ERRO");
        }
    }

    public String[] getRow(int index)  {
        String[] row = new String[cidades[index].length];
        for (int i = 0; i < row.length; i++)
            row[i] = cidades[index][i];
        return row;
    }

}
