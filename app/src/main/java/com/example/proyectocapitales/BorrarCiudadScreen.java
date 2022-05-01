package com.example.proyectocapitales;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BorrarCiudadScreen extends AppCompatActivity {
    EditText etCiudadEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_ciudad_screen);
        etCiudadEliminar = findViewById(R.id.etciudadeliminar);
    }

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "t_lugares", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String ciudad = etCiudadEliminar.getText().toString();

        if (!ciudad.isEmpty()){

            int cantidad = BaseDatabase.delete("t_lugares", "ciudad ='"+ciudad+"'", null);
            BaseDatabase.close();

            etCiudadEliminar.setText("");
            if (cantidad == 1){
                Toast.makeText(this, "Ciudad Eliminada", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "La ciudad ingresada no existe", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this, "Introduce una ciudad para eliminar", Toast.LENGTH_LONG).show();
        }
    }

    public void EliminarTodo(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "t_lugares", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        BaseDatabase.delete("t_lugares",null, null);

        Toast.makeText(this, "Todas las ciudades han sido eliminadas", Toast.LENGTH_LONG).show();
    }

}