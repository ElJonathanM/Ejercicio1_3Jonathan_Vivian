package com.example.ejercicio1_3jonathan_vivian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio1_3jonathan_vivian.Procesos.SQLiteConexion;
import com.example.ejercicio1_3jonathan_vivian.Procesos.Transacciones;

public class ActivityPersonaSeleccionada extends AppCompatActivity {

    EditText txtnombres, txtapellido, txtedad, txtcorreo, txtdireccion, txtcodigo;
    Button btnaactualizar, btneliminar, btnregresar;

    @Override public void onBackPressed() { }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_seleccionada);

        txtcodigo = (EditText) findViewById(R.id.txtcodigo);
        txtnombres = (EditText) findViewById(R.id.txtnombresseleccionado);
        txtapellido = (EditText) findViewById(R.id.txtapellidosseleccionado);
        txtedad = (EditText) findViewById(R.id.txtedadseleccionado);
        txtcorreo = (EditText) findViewById(R.id.txtcorreoseleccionado);
        txtdireccion = (EditText) findViewById(R.id.txtdireccionseleccionado);

        btnaactualizar = (Button) findViewById(R.id.btnactualizar);
        btneliminar = (Button) findViewById(R.id.btneliminar);
        btnregresar = (Button) findViewById(R.id.btnseleccionregresar) ;

        txtcodigo.setText(getIntent().getStringExtra("codigo"));
        txtnombres.setText(getIntent().getStringExtra("nombre"));
        txtapellido.setText(getIntent().getStringExtra("apellidos"));
        txtedad.setText(getIntent().getStringExtra("edad"));
        txtcorreo.setText(getIntent().getStringExtra("correo"));
        txtdireccion.setText(getIntent().getStringExtra("direccion"));

        btnaactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPersona();
            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPersona();
            }
        });

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ActivityLista.class);
                startActivity(intent);
            }
        });
    }



    private void actualizarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = txtcodigo.getText().toString();

        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, txtnombres.getText().toString());
        valores.put(Transacciones.apellidos, txtapellido.getText().toString());
        valores.put(Transacciones.edad, txtedad.getText().toString());
        valores.put(Transacciones.correo, txtcorreo.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion.getText().toString());

        db.update(Transacciones.tablapersonas, valores , Transacciones.id +" = "+obtenerCodigo, null);
        db.close();

        Intent intent = new Intent(getApplicationContext(),ActivityLista.class);
        startActivity(intent);
    }

    private void eliminarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String obtenerCodigo = txtcodigo.getText().toString();

        db.delete(Transacciones.tablapersonas,Transacciones.id +" = "+ obtenerCodigo, null);

        Toast.makeText(getApplicationContext(), "Registro eliminado con exito, Codigo " + obtenerCodigo
                ,Toast.LENGTH_LONG).show();
        db.close();

        //REGRESAR AL MENU PRINCIPAL
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}