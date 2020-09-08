package com.dalda.mycontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    private static final int CODIGO_SOLICITUD_PERMISO = 1;
    private static final int CODIGO_SOLICITUD_HABILITAR_BLUETOOTH = 0;
    private Context context;
    private Activity activity;
    private  String campo_vacio ="Campo Vacio";
    public Contacto contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        /*start inicializando variables para el premiso de bluetooth*/
        context = getApplicationContext();
        activity = this;
        /*end inicializando variables para el premiso de bluetooth*/

        Intent intent = getIntent();

        contact = (Contacto)intent.getSerializableExtra("mycontact");

        TextView tv_nombre = findViewById(R.id.tvnombre);
        tv_nombre.setText(contact.getNombre());

        TextView tv_nacimiento = findViewById(R.id.tvnacimiento);
        tv_nacimiento.setText(contact.getNacimiento());

        TextView tv_telefono = findViewById(R.id.tvtelefono);
        tv_telefono.setText(contact.getTelefono());

        TextView tv_email = findViewById(R.id.tvemail);
        tv_email.setText(contact.getEmail());

        TextView tv_descripcion = findViewById(R.id.tvdescripcion);
        tv_descripcion.setText(contact.getDescripcion());
    }

    public void editContact(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("myeditar", contact);
        startActivity(intent);
        finish();
    }

    /*start inicializando variables para el premiso de bluetooth*/
    public void habilitarBluetooth(View v){
        solicitarPermiso();
        BluetoothAdapter mBluetooAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetooAdapter == null){
            Toast.makeText(EditActivity.this, "Ti dispositivo no tiene bluetooth", Toast.LENGTH_SHORT).show();
        }
        if (!mBluetooAdapter.isEnabled()){
            Intent habilitarBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(habilitarBluetoothIntent, CODIGO_SOLICITUD_HABILITAR_BLUETOOTH);
        }
    }

    public boolean checarStatusPermiso(){
        int resultado = ContextCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH);
        if (resultado == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }

    public void solicitarPermiso(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.BLUETOOTH)){
            Toast.makeText(EditActivity.this, "el permiso ya fue otorgado, si deseas desactivarlo, puedes ir a los ajustes de la aplicacion", Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.BLUETOOTH}, CODIGO_SOLICITUD_PERMISO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                if (checarStatusPermiso()){
                    Toast.makeText(EditActivity.this, "Ya esta activo el permiso para bluetooth", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditActivity.this, "No esta activo el permiso para bluetooth", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /*end inicializando variables para el premiso de bluetooth*/
}