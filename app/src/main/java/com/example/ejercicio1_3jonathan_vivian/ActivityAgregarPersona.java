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

public class ActivityAgregarPersona extends AppCompatActivity {

    EditText txtnombres, txtapellidos, txtedad, txtcorreo, txtdireccion;
    Button btnguardar, btnregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_persona);

        txtnombres = (EditText) findViewById(R.id.txtnombres);
        txtapellidos = (EditText) findViewById(R.id.txtapellidos);
        txtedad = (EditText) findViewById(R.id.txtedad);
        txtcorreo = (EditText) findViewById(R.id.txtcorreo);
        txtdireccion = (EditText) findViewById(R.id.txtdireccion);

        btnguardar = (Button) findViewById(R.id.btnguardar);
        btnregresar = (Button) findViewById(R.id.btnregresar);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agrgegarPersonas();
            }
        });
        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void agrgegarPersonas() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, txtnombres.getText().toString());
        valores.put(Transacciones.apellidos, txtapellidos.getText().toString());
        valores.put(Transacciones.edad, txtedad.getText().toString());
        valores.put(Transacciones.correo, txtcorreo.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablapersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo " + resultado.toString()
                ,Toast.LENGTH_LONG).show();

        db.close();
        limpiarPantalla();

        //REGRESAR AL MENU PRINCIPAL
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    private void limpiarPantalla() {
        txtnombres.setText("");
        txtapellidos.setText("");
        txtedad.setText("");
        txtcorreo.setText("");
        txtdireccion.setText("");
    }
}