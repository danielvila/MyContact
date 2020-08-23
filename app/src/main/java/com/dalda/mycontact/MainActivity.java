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

    public Contacto contac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextnacimiento = (EditText) findViewById(R.id.editTextDate);
        editTextnacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });
       /* editandoContac();*/
    }

    public void showDatePickerDialog(View v) {

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = twoDigits(day) + "/" + twoDigits(month+1) + "/" + year;
                EditText editTextnacimiento = (EditText) findViewById(R.id.editTextDate);
                editTextnacimiento.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
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