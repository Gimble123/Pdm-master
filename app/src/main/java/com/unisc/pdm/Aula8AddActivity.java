package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Aula8AddActivity extends AppCompatActivity {

    private EditText etModelo, etAno, etValor;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula8_add);

        etModelo= findViewById(R.id.etModelo);
        etAno= findViewById(R.id.etAno);
        etValor= findViewById(R.id.etValor);

        helper = new DatabaseHelper(this);
    }

    public void addClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("modelo", etModelo.getText().toString());
        c.put("ano", Integer.parseInt(etAno.getText().toString()));
        c.put("valor", Double.parseDouble(etValor.getText().toString()));

        long res = db.insert("carro", null, c);
        if (res != -1) {
            Toast.makeText(this, "OK added", Toast.LENGTH_SHORT).show();
            etModelo.setText("");
            etAno.setText("");
            etValor.setText("");
        }
        else {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();

    }
}
