package com.tecnologico.basedatos.luis_aguilar.basedatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Consultar extends AppCompatActivity {

    EditText txtedit;
    Button btnconsultar,btnborra;
    TextView id,nombre,ocupacion,telefono,correo,contrasena, vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        vista=(TextView) findViewById(R.id.Vista);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        vista.setText(bundle.getString("Opcion"));



        txtedit=(EditText) findViewById(R.id.TxtConsulta);

        btnconsultar=(Button) findViewById(R.id.Consulta);
        btnborra=(Button) findViewById(R.id.Borrar);

        id=(TextView) findViewById(R.id.ID);
        nombre=(TextView) findViewById(R.id.Nombre);
        ocupacion=(TextView) findViewById(R.id.Ocupacion);
        telefono=(TextView) findViewById(R.id.Telefono);
        correo=(TextView) findViewById(R.id.Correo);
        contrasena=(TextView) findViewById(R.id.Contrasena);
        Accion();


        btnconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(txtedit.getText()).equals(" ") || String.valueOf(txtedit.getText()).equals("")) {
                    Toast.makeText(Consultar.this, "Introduce ID o Nombre", Toast.LENGTH_SHORT).show();
                } else {

                    ContactosOpenHelper admin = new ContactosOpenHelper(Consultar.this, "Administracion", null, 1);
                    SQLiteDatabase bd = admin.getWritableDatabase();
                    String vid="";


                    if (Character.isDigit(txtedit.getText().charAt(0))) {
                        vid = txtedit.getText().toString();
                        Cursor fila = bd.rawQuery("select id, nombre, ocupacion, telefono, correo from contactos where id=" + vid, null);

                        if (fila.moveToFirst()) {
                            id.setText(fila.getString(0));
                            nombre.setText(fila.getString(1));
                            ocupacion.setText(fila.getString(2));
                            telefono.setText(fila.getString(3));
                            correo.setText(fila.getString(4));



                        } else {
                            Toast.makeText(Consultar.this, "El contacto no existe", Toast.LENGTH_SHORT).show();
                            bd.close();
                        }
                    }else if (Character.isLetter(txtedit.getText().charAt(0))){
                        vid = txtedit.getText().toString();
                        Cursor fila1 = bd.rawQuery("select id, nombre, ocupacion, telefono, correo from contactos where nombre like " +"'"+ vid+"'", null);

                        if (fila1.moveToFirst()) {
                            id.setText(fila1.getString(0));
                            nombre.setText(fila1.getString(1));
                            ocupacion.setText(fila1.getString(2));
                            telefono.setText(fila1.getString(3));
                            correo.setText(fila1.getString(4));



                        } else {
                            Toast.makeText(Consultar.this, "El contacto no existe", Toast.LENGTH_SHORT).show();
                            bd.close();
                        }
                    }

                }

            }
        });


        btnborra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(id.getText()).equals("ID")){
                    Toast.makeText(Consultar.this, "Selecciona un contacto", Toast.LENGTH_SHORT).show();
                }
                else{

                    ContactosOpenHelper admin=new ContactosOpenHelper(Consultar.this,"Administracion",null,1);
                    SQLiteDatabase bd=admin.getWritableDatabase();
                    String vid=id.getText().toString();
                    int cant=bd.delete("contactos", "id="+vid,null);
                    bd.close();
                    id.setText("");
                    nombre.setText("Selecciona un contacto");
                    ocupacion.setText("");
                    telefono.setText("");
                    correo.setText("");


                    if(cant==1){
                        Toast.makeText(Consultar.this,"Se borro el contacto",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Consultar.this,"contacto inexistente",Toast.LENGTH_SHORT).show();
                    }



                }

            }
        });

    }

    public void Accion(){

        if (String.valueOf(vista.getText()).equals("Consultar")){
            btnborra.setVisibility(View.INVISIBLE);
        }


    }
}
