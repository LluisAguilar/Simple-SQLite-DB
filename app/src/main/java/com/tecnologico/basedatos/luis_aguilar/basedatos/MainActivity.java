package com.tecnologico.basedatos.luis_aguilar.basedatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button agrega,consulta,borra,modificar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agrega=(Button) findViewById(R.id.agregar);
        consulta=(Button) findViewById(R.id.consultar);
        borra=(Button) findViewById(R.id.borrar);
        modificar=(Button) findViewById(R.id.Modificar);


        agrega.setOnClickListener(this);
        consulta.setOnClickListener(this);
        borra.setOnClickListener(this);
        modificar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.agregar:
                Intent intent=new Intent(this,Agregar.class);
                startActivity(intent);
                break;
            case R.id.consultar:
                Intent intent2=new Intent(this,Consultar.class);
                intent2.putExtra("Opcion","Consultar");
                startActivity(intent2);

                break;
            case R.id.borrar:
                Intent intent3=new Intent(this,Consultar.class);
                intent3.putExtra("Opcion","Borrar");
                startActivity(intent3);

                break;
            case R.id.Modificar:
                Intent intent4=new Intent(this,Modificar.class);
                startActivity(intent4);

                break;


        }
    }
}
