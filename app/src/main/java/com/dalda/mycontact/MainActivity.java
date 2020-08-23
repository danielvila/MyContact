package com.dalda.mycontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String nombre = "";
    private String nacimiento = "";
    private String telefono = "";
    private String email = "";
    private String descripcion = "";

    public Contacto contac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* editandoContac();*/
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void saveContact(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        EditText editTextnombre = (EditText) findViewById(R.id.editTextTextPersonName);
        nombre = editTextnombre.getText().toString();

        EditText editTextnacimiento = (EditText) findViewById(R.id.editTextDate);
        nacimiento = editTextnacimiento.getText().toString();

        EditText editTexttelefono = (EditText) findViewById(R.id.editTextPhone);
        telefono = editTexttelefono.getText().toString();

        EditText editTextemail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        email = editTextemail.getText().toString();

        EditText editTextdescripcion = (EditText) findViewById(R.id.editTextTextMultiLine);
        descripcion = editTextdescripcion.getText().toString();

        contac = new Contacto(nombre, nacimiento, telefono, email, descripcion);
        intent.putExtra("mycontact", contac);
        startActivity(intent);
        finish();
    }

    public void editandoContac(){
        Intent intent = getIntent();
        contac = (Contacto)intent.getSerializableExtra("myeditar");

        /*EditText tv_nombre = findViewById(R.id.editTextTextPersonName);
        tv_nombre.setText(contac.getNombre());

        EditText tv_nacimiento = findViewById(R.id.editTextDate);
        tv_nacimiento.setText(contac.getNacimiento());

        EditText tv_telefono = findViewById(R.id.editTextPhone);
        tv_telefono.setText(contac.getTelefono());

        EditText tv_email = findViewById(R.id.editTextTextEmailAddress);
        tv_email.setText(contac.getEmail());

        EditText tv_descripcion = findViewById(R.id.editTextTextMultiLine);
        tv_descripcion.setText(contac.getDescripcion());*/
    }
}