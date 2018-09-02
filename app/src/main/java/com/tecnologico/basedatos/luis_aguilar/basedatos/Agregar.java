package com.tecnologico.basedatos.luis_aguilar.basedatos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Agregar extends AppCompatActivity {

    EditText id,nombre,ocupacion,telefono,correo,contrasena;
    Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        id=(EditText) findViewById(R.id.ID);
        nombre=(EditText) findViewById(R.id.Nombre);
        ocupacion=(EditText) findViewById(R.id.Ocupacion);
        telefono=(EditText) findViewById(R.id.Telefono);
        correo=(EditText) findViewById(R.id.Correo);
        contrasena=(EditText) findViewById(R.id.Contrasena);

        agregar=(Button) findViewById(R.id.Agregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

if (String.valueOf(id.getText()).equals("") || String.valueOf(id.getText()).equals(" ")
        || String.valueOf(nombre.getText()).equals(" ") || String.valueOf(nombre.getText()).equals("")
        || String.valueOf(telefono.getText()).equals(" ") || String.valueOf(telefono.getText()).equals("")){
    Toast.makeText(Agregar.this,"Debes Agregar id, nombre y telefono como minimo",Toast.LENGTH_LONG).show();
}else{
                    ContactosOpenHelper admin= new ContactosOpenHelper(Agregar.this,"Administracion",null,1);
                    SQLiteDatabase bd = admin.getWritableDatabase();


                    ContentValues registro= new ContentValues();
                    registro.put("id",id.getText().toString());
                    registro.put("nombre", nombre.getText().toString());
                    registro.put("ocupacion", ocupacion.getText().toString());
                    registro.put("telefono", telefono.getText().toString());
                    registro.put("correo", correo.getText().toString());
                    registro.put("contrasena", contrasena.getText().toString());

                    bd.insert("contactos", null, registro);
                    bd.close();
                    id.setText("");
                    nombre.setText("");
                    ocupacion.setText("");
                    telefono.setText("");
                    correo.setText("");
                    contrasena.setText("");

                    Toast.makeText(Agregar.this, "Se guardaron los datos", Toast.LENGTH_SHORT).show();


            }}
        });


    }
}
