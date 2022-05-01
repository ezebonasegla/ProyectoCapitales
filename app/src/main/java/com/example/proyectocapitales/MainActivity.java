package com.example.proyectocapitales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etPais,etCiudadCapital,etPoblacion;
    Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPais = findViewById(R.id.etpais);
        etCiudadCapital = findViewById(R.id.etciudadcapital);
        etPoblacion = findViewById(R.id.etpoblacion);
        btnAgregar = findViewById(R.id.buttonAgregar);

    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "mundo.db", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String ciudad = etCiudadCapital.getText().toString();
        String pais = etPais.getText().toString();
        String poblacion = etPoblacion.getText().toString();

        if (!ciudad.isEmpty() && !pais.isEmpty() && !poblacion.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("ciudad", ciudad);
            registro.put("pais", pais);
            registro.put("poblacion", poblacion);

            BaseDeDatos.insert("t_lugares", null, registro);

            BaseDeDatos.close();
            limpiar();

            Toast.makeText(MainActivity.this, "Ciudad Guardada", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(MainActivity.this, "Completar todos los campos", Toast.LENGTH_LONG).show();
        }
    }



    private void limpiar(){
        etPais.setText("");
        etCiudadCapital.setText("");
        etPoblacion.setText("");
    }

    public void irConsultar(View v){
        Intent i = new Intent(this, ConsultarScreen.class);
        startActivity(i);
    }

    public void irBorrar(View v){
        Intent i = new Intent(this, BorrarCiudadScreen.class);
        startActivity(i);
    }

    public void irModificar(View v){
        Intent i = new Intent(this, ModificarScreen.class);
        startActivity(i);
    }
}