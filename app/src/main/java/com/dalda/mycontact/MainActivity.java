package com.dalda.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private String nombre = "";
    private String nacimiento = "";
    private String telefono = "";
    private String email = "";
    private String descripcion = "";

    private DatePicker birthdate;
    private int day;
    private int month;
    private int monthString;
    private int year;

    public Contacto contac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editandoContac();
    }

    public void saveContact(View view) {
        EditText editTextnombre = (EditText) findViewById(R.id.editTextTextPersonName);
        nombre = editTextnombre.getText().toString();

        birthdate   = (DatePicker) findViewById(R.id.dpBirthdate);
        day          = birthdate.getDayOfMonth();
        month        = birthdate.getMonth();
        monthString  = month + 1; // Increment 1 month to correct string.
        year         = birthdate.getYear();
        nacimiento  = day + "/" + monthString + "/" + year; // Date string to show.

        EditText editTexttelefono = (EditText) findViewById(R.id.editTextPhone);
        telefono = editTexttelefono.getText().toString();

        EditText editTextemail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        email = editTextemail.getText().toString();

        EditText editTextdescripcion = (EditText) findViewById(R.id.editTextTextMultiLine);
        descripcion = editTextdescripcion.getText().toString();

        SharedPreferences miPrefenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPrefenciaCompartida.edit();

        editor.putString("nombre", nombre);
        editor.putString("correo", email);

        editor.commit();

        Toast.makeText(MainActivity.this, "Se ha creado la preferencia compartida", Toast.LENGTH_SHORT).show();
        editTextnombre.setText("");
        editTextemail.setText("");

        /*abajo codigo para enviar datos a otra activity y cambiar de activity */
       /*
        Intent intent = new Intent(this, EditActivity.class);
        contac = new Contacto(nombre, nacimiento, telefono, email, descripcion, year, month, day);
        intent.putExtra("mycontact", contac);
        startActivity(intent);
        finish();*/
    }

    public void mostrarPrefencia(View view) {
        SharedPreferences miPrefenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        nombre = miPrefenciaCompartida.getString("nombre", "no existe esta varable");
        email = miPrefenciaCompartida.getString("correo", "no existe esta varable");
        String preferencia = "\nNombre: " + nombre + "\nCorreo: " +email;
        TextView tvPrefernciaCompartida = (TextView) findViewById(R.id.tvPrefernciaCompartida);
        tvPrefernciaCompartida.setText(preferencia);
    }

    public void editandoContac(){
        Intent intent = getIntent();

        contac = (Contacto)intent.getSerializableExtra("myeditar");
        if (contac != null) {
            EditText tv_nombre = findViewById(R.id.editTextTextPersonName);
            tv_nombre.setText(contac.getNombre());

            int extraYear    = contac.getAnio();
            int extraMonth   = contac.getMes();
            int extraDay     = contac.getDia();
            birthdate   = (DatePicker) findViewById(R.id.dpBirthdate);
            birthdate.updateDate(extraYear, extraMonth, extraDay);

            EditText tv_telefono = findViewById(R.id.editTextPhone);
            tv_telefono.setText(contac.getTelefono());

            EditText tv_email = findViewById(R.id.editTextTextEmailAddress);
            tv_email.setText(contac.getEmail());

            EditText tv_descripcion = findViewById(R.id.editTextTextMultiLine);
            tv_descripcion.setText(contac.getDescripcion());
        }
    }

    public void generarArchivo(View v){
        try {
            EditText editTextnombre = (EditText) findViewById(R.id.editTextTextPersonName);
            nombre = editTextnombre.getText().toString();

            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_APPEND);
            outputStream.write(nombre.getBytes());
            outputStream.close();
            Toast.makeText(MainActivity.this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
            editTextnombre.setText("");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Hubo un error en la escritura del archivo", Toast.LENGTH_SHORT).show();
        }
    }
}