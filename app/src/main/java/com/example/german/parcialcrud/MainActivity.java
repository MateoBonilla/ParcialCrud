package com.example.german.parcialcrud;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Edt_nombre, Edt_apellido, Edt_edad;
    Button bt_agregar, bt_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Edt_nombre = (EditText) findViewById(R.id.Edt_nombre);
        Edt_apellido = (EditText) findViewById(R.id.Edt_apellido);
        Edt_edad = (EditText) findViewById(R.id.Edt_edad);

        bt_agregar = (Button) findViewById(R.id.bt_agregar);
        bt_mostrar = (Button) findViewById(R.id.bt_mostrar);

        bt_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar(Edt_nombre.getText().toString(), Edt_apellido.getText().toString(), Edt_edad.getText().toString());
            }
        });

        bt_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Listar.class));
            }
        });
    }

    private void agregar (String Nombre, String Apellido, String Edad){
        DBHelper helper = new DBHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {

            ContentValues c = new ContentValues();
            c.put("Nombre", Nombre);
            c.put("Apellido", Apellido);
            c.put("Edad", Edad);

            db.insert("PERSONAS",null,c);
            db.close();
            Toast.makeText(this,"Datos Insertados correctamente",Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(this,"Error: "+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
