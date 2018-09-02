package com.tecnologico.basedatos.luis_aguilar.basedatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btnOk;
    EditText user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnOk=(Button) findViewById(R.id.btnAceptar);
        user=(EditText) findViewById(R.id.usuario);
        pass=(EditText) findViewById(R.id.contrasena);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactosOpenHelper admin = new ContactosOpenHelper(Login.this, "Administracion", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                String vid = "";
                String vid2= "";


                    vid = user.getText().toString();
                    vid2=pass.getText().toString();
                    Cursor fila = bd.rawQuery("select nombre from contactos where nombre like "+"'"+vid+"'"+" and contrasena like "+"'"+vid2+"'", null);

                    if (fila.moveToFirst()) {
                        Intent intent= new Intent(Login.this,MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Login.this, "El contacto no existe", Toast.LENGTH_SHORT).show();
                        bd.close();
                    }


            }
        });

    }
}
