package com.tecnologico.basedatos.luis_aguilar.basedatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {
    EditText id,nombre,ocupacion,telefono,correo,contrasena, txtedit;
    Button buscar, modificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        buscar=(Button) findViewById(R.id.Buscar);
        modificar=(Button) findViewById(R.id.Modificar);

        id=(EditText) findViewById(R.id.ID);
        nombre=(EditText) findViewById(R.id.Nombre);
        ocupacion=(EditText) findViewById(R.id.Ocupacion);
        telefono=(EditText) findViewById(R.id.Telefono);
        correo=(EditText) findViewById(R.id.Correo);
        contrasena=(EditText) findViewById(R.id.Contrasena);
        txtedit=(EditText) findViewById(R.id.TxtConsulta);


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (String.valueOf(txtedit.getText()).equals(" ") || String.valueOf(txtedit.getText()).equals("")) {
                    Toast.makeText(Modificar.this, "Introduce ID o Nombre", Toast.LENGTH_SHORT).show();
                } else {

                    ContactosOpenHelper admin = new ContactosOpenHelper(Modificar.this, "Administracion", null, 1);
                    SQLiteDatabase bd = admin.getWritableDatabase();
                    String vid="";


                    if (Character.isDigit(txtedit.getText().charAt(0))) {
                        vid = txtedit.getText().toString();
                        Cursor fila = bd.rawQuery("select id, nombre, ocupacion, telefono, correo, contrasena from contactos where id=" + vid, null);

                        if (fila.moveToFirst()) {
                            id.setText(fila.getString(0));
                            nombre.setText(fila.getString(1));
                            ocupacion.setText(fila.getString(2));
                            telefono.setText(fila.getString(3));
                            correo.setText(fila.getString(4));
                            contrasena.setText(fila.getString(5));


                        } else {
                            Toast.makeText(Modificar.this, "El contacto no existe", Toast.LENGTH_SHORT).show();
                            bd.close();
                        }
                    }else if (Character.isLetter(txtedit.getText().charAt(0))){
                        vid = txtedit.getText().toString();
                        Cursor fila1 = bd.rawQuery("select id, nombre, ocupacion, telefono, correo, contrasena from contactos where nombre like " +"'"+ vid+"'", null);

                        if (fila1.moveToFirst()) {
                            id.setText(fila1.getString(0));
                            nombre.setText(fila1.getString(1));
                            ocupacion.setText(fila1.getString(2));
                            telefono.setText(fila1.getString(3));
                            correo.setText(fila1.getString(4));
                            contrasena.setText(fila1.getString(5));


                        } else {
                            Toast.makeText(Modificar.this, "El contacto no existe", Toast.LENGTH_SHORT).show();
                            bd.close();
                        }
                    }

                }

            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactosOpenHelper admin= new ContactosOpenHelper(Modificar.this,"Administracion",null,1);
                SQLiteDatabase bd= admin.getWritableDatabase();

                String vid= id.getText().toString();
                ContentValues registro= new ContentValues();
                registro.put("id",id.getText().toString());
                registro.put("nombre",nombre.getText().toString());
                registro.put("ocupacion",ocupacion.getText().toString());
                registro.put("telefono", telefono.getText().toString());
                registro.put("correo", correo.getText().toString());
                registro.put("contrasena", contrasena.getText().toString());

                int cant=bd.update("contactos",registro,"id="+vid,null);
                bd.close();
                if(cant==1){
                    Toast.makeText(Modificar.this,"Se actualizo el contacto",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Modificar.this,"No se actualizo el contacto",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
