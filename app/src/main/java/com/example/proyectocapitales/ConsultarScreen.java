package com.example.proyectocapitales;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultarScreen extends AppCompatActivity {
    EditText etCiudadConsulta, etNombrePais, etNombreCiudad, etCantPoblacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_screen);
        etCiudadConsulta=findViewById(R.id.etciudadConsulta);
        etNombrePais=findViewById(R.id.etnombrePais);
        etNombreCiudad=findViewById(R.id.etnombreCiudad);
        etCantPoblacion=findViewById(R.id.etcantPoblacion);
    }

    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "mundo.db", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        String ciudad = etCiudadConsulta.getText().toString();

        if(!ciudad.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("SELECT ciudad, pais, poblacion FROM t_lugares WHERE ciudad ='"+ciudad+"'", null);

            if (fila.moveToFirst()){
                etNombreCiudad.setText(fila.getString(0));
                etNombrePais.setText(fila.getString(1));
                etCantPoblacion.setText(fila.getString(2));
            }else {
                Toast.makeText(this, "No existe la Ciudad", Toast.LENGTH_SHORT).show();
                limpiar();

            }
            fila.close();
            BaseDeDatos.close();
        }else{
            Toast.makeText(this, "Introduce una ciudad para consultar", Toast.LENGTH_SHORT).show();
            limpiar();
        }

    }

    private void limpiar(){
        etNombrePais.setText("");
        etNombreCiudad.setText("");
        etCantPoblacion.setText("");
    }

}