package com.dalda.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    private  String campo_vacio ="Campo Vacio";
    public Contacto contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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
}