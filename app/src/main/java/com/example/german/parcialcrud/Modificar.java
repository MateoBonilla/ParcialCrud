package com.example.german.parcialcrud;

import android.database.sqlite.SQLiteDatabase;
import android.os.MessageQueue;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {

    EditText Edt_nombre, Edt_apellido, Edt_edad;
    Button bt_modificar, bt_borrar;

    int id;
    String nombre, apellido, edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle b = getIntent().getExtras();

        if(b!=null){
            id = b.getInt("Id");
            nombre=b.getString("Nombre");
            apellido=b.getString("Apellido");
            edad=b.getString("Edad");
        }


        Edt_nombre = (EditText) findViewById(R.id.Edt_nombre);
        Edt_apellido = (EditText) findViewById(R.id.Edt_apellido);
        Edt_edad = (EditText) findViewById(R.id.Edt_edad);

        Edt_nombre.setText(nombre);
        Edt_apellido.setText(apellido);
        Edt_edad.setText(edad);

        bt_modificar = (Button) findViewById(R.id.bt_modificar);
        bt_borrar = (Button) findViewById(R.id.bt_borrar);

        bt_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });


        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modificar(id, Edt_nombre.getText().toString(), Edt_apellido.getText().toString(), Edt_edad.getText().toString());
                onBackPressed();
            }
        });

    }

    private void Modificar(int Id, String Nombre, String Apellido, String Edad){

        DBHelper helper = new DBHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql ="update Personas set Nombre='"+ Nombre + "',Apellido='" + Apellido +  "',Edad='" + Edad + "' where Id="+Id;
        db.execSQL(sql);
        db.close();

    }

    private void Eliminar(int Id){

        DBHelper helper = new DBHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql ="delete from Personas where Id="+Id;
        db.execSQL(sql);
        db.close();

    }

}
