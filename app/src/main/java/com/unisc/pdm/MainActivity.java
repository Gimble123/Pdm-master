package com.unisc.pdm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aula1Click(View view) {
        startActivity(new Intent(this,Aula1Activity.class));
    }

    public void aula2Click(View view) { startActivity(new Intent(this,Aula2Activity.class));  }

    public void aula3Click(View view) {
        startActivity(new Intent(this,Aula3Activity.class));
    }

    public void aula4Click(View view) {
        startActivity(new Intent(this,Aula4Activity.class));
    }

    public void aula5Click(View view) { startActivity(new Intent(this,Aula5Activity.class)); }

    public void aula6Click(View view) {
        startActivity(new Intent(this,Aula6Activity.class));
    }

    public void aula7Click(View view) {
        startActivity(new Intent(this,Aula7Activity.class));
    }

    public void aula8Click(View view) {
        startActivity(new Intent(this,Aula8Activity.class));
    }

    public void aula9Click(View view) { startActivity(new Intent(this,Aula9Activity.class));  }

    public void aula10Click(View view) {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage("com.ghelfer.appjni");
        if (intent != null) {
            startActivity(intent);
        }
    }

    public void aula11Click(View view) { startActivity(new Intent(this,Aula11Activity.class)); }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    runCameraActivity();
                }
                return true;
        }
        return false;
    }

    private void runCameraActivity() {
        Intent intent = new Intent(this, TrabalhoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            if (requestCode == 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MessageBox("Camera permission granted!");
                    runCameraActivity();
                } else {
                    MessageBox("Camera permission denied!");
                }
            }
        } else {
            MessageBox("Camera permission denied!");
        }
    }

    void MessageBox(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }


}
