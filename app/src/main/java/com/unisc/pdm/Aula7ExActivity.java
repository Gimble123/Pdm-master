package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Aula7ExActivity extends AppCompatActivity {

    private EditText txtCEP;
    private TextView txtLogradouro;
    private TextView txtComplemento;
    private TextView txtBairro;
    private TextView txtLocalidade;
    private TextView txtUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula7_ex);

        txtCEP = findViewById(R.id.txtCEP);
        txtLogradouro = findViewById(R.id.txtLogradouro);
        txtComplemento = findViewById(R.id.txtComplemento);
        txtBairro = findViewById(R.id.txtBairro);
        txtLocalidade = findViewById(R.id.txtLocalidade);
        txtUf = findViewById(R.id.txtUf);

    }

    public void onClickBuscar(View view) {
        String cep  = txtCEP.getText().toString();
        HttpAsyncTask task = new HttpAsyncTask();
        task.execute(cep);
    }

    class HttpAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Aula7ExActivity.this);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://viacep.com.br/ws/" + strings[0] + "/json");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int status = con.getResponseCode();
                if (status==200) {
                    InputStream stream = new BufferedInputStream(con.getInputStream());
                    BufferedReader buff = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();
                    String str = "";
                    while ((str = buff.readLine()) != null) {
                        builder.append(str);
                    }
                    con.disconnect();
                    return builder.toString();
                }
            }
            catch (Exception ex) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dialog.dismiss();

            if (result != null) {
                try {
                    JSONObject obj = new JSONObject(result);
                    txtLogradouro.setText(obj.getString("logradouro"));
                    txtComplemento.setText(obj.getString("complemento"));
                    txtBairro.setText(obj.getString("bairro"));
                    txtLocalidade.setText(obj.getString("localidade"));
                    txtUf.setText(obj.getString("uf"));

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        }
    }
}
