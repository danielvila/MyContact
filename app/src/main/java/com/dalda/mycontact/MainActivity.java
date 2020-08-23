package com.dalda.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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
        Intent intent = new Intent(this, EditActivity.class);
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

        contac = new Contacto(nombre, nacimiento, telefono, email, descripcion, year, month, day);
        intent.putExtra("mycontact", contac);
        startActivity(intent);
        finish();
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
}