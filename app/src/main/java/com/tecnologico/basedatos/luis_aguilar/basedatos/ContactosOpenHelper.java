package com.tecnologico.basedatos.luis_aguilar.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Luis_Aguilar on 16/11/2016.
 */
public class ContactosOpenHelper extends SQLiteOpenHelper {
    public ContactosOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contactos(id integer primary key,nombre text,ocupacion text,telefono integer,correo text,contrasena text)");
        db.execSQL("insert into contactos values(1, 'admin', 'maestro', 99999999, 'correo@hotmail.com', 'admin')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if EXISTS contactos");
        db.execSQL("create table contactos(id integer primary key,nombre text,ocupacion text,telefono integer,correo text,contrasena text)");
        db.execSQL("insert into contactos values(1, 'admin', 'maestro', 99999999, 'correo@hotmail.com', 'admin')");
    }
}
