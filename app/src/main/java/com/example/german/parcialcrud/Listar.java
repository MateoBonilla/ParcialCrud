package com.example.german.parcialcrud;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;


    @Override
    protected void onPostResume() {
        super.onPostResume();
        cargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listView = (ListView) findViewById(R.id.listView);

        cargarListado();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Listar.this, listado.get(position), Toast.LENGTH_SHORT).show();

                int id2= Integer.parseInt(listado.get(position).split(" ")[0]);
                String Nombre=listado.get(position).split(" ")[1];
                String Apellido=listado.get(position).split(" ")[2];
                String Edad=listado.get(position).split(" ")[3];

                Intent intent = new Intent(Listar.this, Modificar.class);
                intent.putExtra("Id", id2);
                intent.putExtra("Nombre", Nombre);
                intent.putExtra("Apellido", Apellido );
                intent.putExtra("Edad", Edad);

                startActivity(intent);

            }
        });

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void cargarListado(){

        listado = ListarPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);

    }

    private ArrayList<String> ListarPersonas(){
        ArrayList<String> datos = new ArrayList<String>();
        DBHelper helper = new DBHelper(this,"Demo",null,1);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "select Id, Nombre, Apellido, Edad from Personas";
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do {
                String linea = c.getInt(0) +" "+ c.getString(1)+" "+ c.getString(2)+" "+c.getString(3);
                datos.add(linea);


            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }



}
