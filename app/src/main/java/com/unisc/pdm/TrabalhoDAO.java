package com.unisc.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TrabalhoDAO implements ITrabalhoDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TrabalhoDAO(Context context) {
        DBHelper db = new DBHelper( context );
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Rgb rgb) {

        ContentValues cv = new ContentValues();
        cv.put("R",  rgb.getR());
        cv.put("G", rgb.getB());
        cv.put("B", rgb.getB());

        try {
            escreve.insert(DBHelper.TABELA_CORES, null, cv );
            Log.i("INFO", "Cores salva com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar cores " + e.getMessage() );
            return false;
        }

        return true;

    }

    @Override
    public List<Rgb> listar() {

        List<Rgb> rgbs = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_CORES + ";";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext() ) {

            Rgb rgb = new Rgb();

            rgb.setR( c.getString( c.getColumnIndex("R") ) );
            rgb.setG( c.getString( c.getColumnIndex("G") ) );
            rgb.setB( c.getString( c.getColumnIndex("B") ) );
            rgb.setNomes(c.getString( c.getColumnIndex("nomes") )  );








        }

        return null;
    }
}
