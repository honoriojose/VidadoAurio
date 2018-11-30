package com.honoriojose.vidadoaurio;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    RecyclerView VidaRecyclerView;
    VidaAdapter VidaAdapter;
    Dialog addItemDialog;
    ImageView newImage;
    EditText newDescription;
    Button savaButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VidaRecyclerView = findViewById(R.id.fotos);
        VidaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        VidaAdapter = new VidaAdapter(this);
        VidaRecyclerView.setAdapter(VidaAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });
    }

    public void showDialog() {
        addItemDialog = new Dialog(MainActivity.this);
        addItemDialog.setContentView(R.layout.add_item_dialog);

        newImage = addItemDialog.findViewById(R.id.new_image);
        newImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermissionGranted()){
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent,12);
                }
                else{
                    Toast.makeText(MainActivity.this,"Voce nao tem permissao", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new  String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 45);
                }
            }
        });
        savaButton = addItemDialog.findViewById(R.id.save_button);
        savaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemDialog.dismiss();
            }
        });

        addItemDialog.setTitle("Adicionar novo item");
        addItemDialog.show();

        }
        public boolean isStoragePermissionGranted () {
            return ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK){
            Uri uri = data.getData();
            Log.i("MainActivity", "Localizacao do Ficheiro:" + uri.getPath());
            String path = getRealPathFromURI(uri);
            File file = new File(path);
            Picasso.get().load(file).into(newImage);
        }
    }

    public String getRealPathFromURI(Uri contentURI){
        Cursor cursor = getContentResolver().query(contentURI,
                null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        String result = cursor.getString(idx);
        cursor.close();
        return result;
    }

    public void openActivity (View view){
            Intent intent = new Intent(this, VidaActivity.class);
            startActivity(intent);
        }
    }

