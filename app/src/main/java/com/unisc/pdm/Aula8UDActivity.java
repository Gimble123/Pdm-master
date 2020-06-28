package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Aula8UDActivity extends AppCompatActivity {

    private TextView tvId;
    private EditText etModelo, etAno, etValor;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula8_ud);

        tvId = findViewById(R.id.tvId);
        etModelo= findViewById(R.id.etModelo);
        etAno= findViewById(R.id.etAno);
        etValor= findViewById(R.id.etValor);
        helper = new DatabaseHelper(this);

        Intent intent = getIntent();
        tvId.setText( intent.getStringExtra("id"));
        etModelo.setText( intent.getStringExtra("modelo"));
        etAno.setText( intent.getStringExtra("ano"));
        etValor.setText(intent.getStringExtra("valor"));


    }

    public void delClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] where = new String[]{ tvId.getText().toString() };

        long res = db.delete("carro", "id = ?", where);
        if (res != -1) {
            Toast.makeText(this, "OK deleted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }


    }

    public void updateClick(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] where = new String[]{ tvId.getText().toString() };

        ContentValues c = new ContentValues();
        c.put("modelo", etModelo.getText().toString());
        c.put("ano", Integer.parseInt(etAno.getText().toString()));
        c.put("valor", Double.parseDouble(etValor.getText().toString()));

        long res = db.update("carro", c,"id = ?", where);
        if (res != -1) {
            Toast.makeText(this, "OK updated", Toast.LENGTH_SHORT).show();
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
