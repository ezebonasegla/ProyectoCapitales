package com.example.proyectocapitales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarScreen extends AppCompatActivity {
    EditText etCiudadMod, etPoblacionMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_screen);
        etCiudadMod = findViewById(R.id.etciudadmod);
        etPoblacionMod = findViewById(R.id.etpoblacionmod);
    }

    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "t_lugares", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String ciudad = etCiudadMod.getText().toString();
        String poblacion = etPoblacionMod.getText().toString();

        if (!ciudad.isEmpty() && !poblacion.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put("poblacion", poblacion);

            int cantidad = BaseDatabase.update("t_lugares", registro, "ciudad ='" + ciudad + "'", null);
            BaseDatabase.close();

            etCiudadMod.setText("");
            etPoblacionMod.setText("");
            if (cantidad == 1){
                Toast.makeText(this, "Poblacion Modificada", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "La ciudad ingresada no existe", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}