package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Aula8ExActivity extends AppCompatActivity {

    EditText etUser, etPass; //activity_aula8_ex.xml
    EditText etUser1, etPass1,etPass2; //layout_aula8_cadastro.xml
    TextView tvSessao; //layout_aula8_home.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView1();
    }

    private void setView1() {
        setContentView(R.layout.activity_aula8_ex);

        etUser = findViewById(R.id.etUser);
        etPass= findViewById(R.id.etPass);
    }

    private void setView2() {
        setContentView(R.layout.layout_aula8_cadastro);
        etUser1= findViewById(R.id.etUser1);
        etPass1= findViewById(R.id.etPass1);
        etPass2= findViewById(R.id.etPass2);
    }

    private void setView3() {
        setContentView(R.layout.layout_aula8_home);
        tvSessao= findViewById(R.id.tvSessao);
    }

    public void loginClick(View view) {
        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();

        if (user.equals("") || pass.equals("")) {
            messageBox("campos em branco!");
            return;
        }
        SharedPreferences settings = getSharedPreferences(" UserInfo", MODE_PRIVATE);
        String userSettings = settings.getString("user", "");
        String passSettings = settings.getString("pass", "");
        int session = settings.getInt("session", 0);

        if (user.equals(userSettings) || pass.equals(passSettings)) {
            setView3();
            session++;
            tvSessao.setText("Sessão #" + session);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("session", session);
            editor.commit();
            editor.apply();
        }
        else {
            messageBox("erro de usuario/senha");
            setView2();
        }

    }

    public void gravarClick(View view) {
        String user1 = etUser1.getText().toString();
        String pass1 = etPass1.getText().toString();
        String pass2 = etPass2.getText().toString();

        if (user1.equals("") || pass1.equals("") || pass2.equals("")) {
            messageBox("campos em branco!");
            return;
        }

        if (!pass1.equals(pass2)) {
            messageBox("senhas diferentes!");
            return;
        }
        SharedPreferences settings = getSharedPreferences(" UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user", user1);
        editor.putString("pass", pass1);
        editor.commit();
        editor.apply();

        messageBox("gravado ok");
    }

    public void logoutClick(View view) {
        SharedPreferences settings = getSharedPreferences(" UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user", "");
        editor.putString("pass", "");
        editor.putInt("session", 0);
        editor.commit();
        editor.apply();

        messageBox("logout ok");
    }

    public void voltarClick(View view) {
        setView1();
    }

    private void messageBox(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
