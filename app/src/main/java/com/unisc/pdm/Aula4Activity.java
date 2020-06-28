package com.unisc.pdm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Aula4Activity extends AppCompatActivity {

    private TextView txtNome, txtTelefone;
    private ImageView imageView;
    private final int SELECIONAR_CONTATO = 0;
    private final int ACIONAR_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula4);

        txtNome = findViewById(R.id.txtNome);
        txtTelefone = findViewById(R.id.txtTelefone);
        imageView = findViewById(R.id.imageView);
    }

    public void contatoClick(View view) {
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, SELECIONAR_CONTATO);
    }

    public void webClick(View view) {
        Uri uri = Uri.parse("http://www.unisc.br");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void callClick(View view) {
        Uri uri = Uri.parse("tel:519887712345");
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Não foi concedida permissão para ligações", Toast.LENGTH_SHORT).show();
            return;
        }
        startActivity(intent);
    }

    public void maps1Click(View view) {
        Uri uriGeo = Uri.parse("geo:0,0?q=UNISC, Santa+Cruz+do+Sul");
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uriGeo);
        startActivity(intent);
    }

    public void maps2Click(View view) {
        String local = "geo:-25.443195,-49.280977";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(local));
        startActivity(intent);
    }

    public void maps3Click(View view) {
        String partida = "-29.6976663, -52.43867749999998";
        String destino = "-29.7176521, -52.42735859999999";
        String url = "http://maps.google.com/maps?f=d&saddr="+partida+"&daddr="+destino+"&hl=pt";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }


    public void cameraClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ACIONAR_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECIONAR_CONTATO){
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();

                Cursor c = getContentResolver().query(uri, null, null, null, null);
                c.moveToNext();
                int nameCol = c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
                int idCol = c.getColumnIndexOrThrow(ContactsContract.Contacts._ID);
                String name = c.getString(nameCol);
                String id = c.getString(idCol);
                c.close();

                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                phones.moveToNext();
                String phoneNumber = phones.getString(phones.getColumnIndexOrThrow (ContactsContract.CommonDataKinds.Phone.NUMBER));
                phones.close();

                txtNome.setText(name);
                txtTelefone.setText(phoneNumber);

            }else{ //RESULT_CANCELED
                Toast.makeText(this,"Nenhum contato selecionado", Toast.LENGTH_SHORT).show();
            }
        }
        else { //ACIONAR_CAMERA
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap bm = (Bitmap) extras.get("data");
                imageView.setImageBitmap(bm);
            }
            else {
                Toast.makeText(this,"Nenhuma foto foi tirada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
