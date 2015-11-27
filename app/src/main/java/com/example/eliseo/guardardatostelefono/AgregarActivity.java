package com.example.eliseo.guardardatostelefono;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class AgregarActivity extends AppCompatActivity {
    EditText nombre, edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        nombre = (EditText) findViewById(R.id.nombre);
        edad = (EditText) findViewById(R.id.edad);

    }

    public void save(View v) {
        if (nombre.getText().toString().equals("")) nombre.requestFocus();
        else if (edad.getText().toString().equals("")) nombre.requestFocus();
        else {
            try {
                int nedad = Integer.parseInt(edad.getText().toString());

                guardar(nombre.getText().toString(), nedad);

            } catch (NumberFormatException n) {
                edad.setText("");
                edad.requestFocus();

            }

        }
    }

    public void guardar(String snombre, int sedad) {
        File extDir = Environment.getExternalStorageDirectory();
        String FILE_NAME = extDir.getAbsolutePath() + "/Base.txt"; //"/storage/extSdCard/Datos.txt";

        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                FileOutputStream fos = new FileOutputStream(new File(FILE_NAME), true);
                String id = leerId();
                String cadena = id + "-" + snombre + "-" + sedad + "\r\n";

                fos.write(cadena.getBytes());
                fos.close();

                nombre.setText("");
                edad.setText("");
                nombre.requestFocus();
                Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_SHORT).show();

            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
                Toast.makeText(getBaseContext(), "Solo lectura", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(), "No es posible acceder a la Micro SD", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    public String leerId() {
        File extDir = Environment.getExternalStorageDirectory();
        String FILE_NAME = extDir.getAbsolutePath() + "/Base.txt";
        int ultimoId = 0;
        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                FileInputStream in = new FileInputStream(new File(FILE_NAME));
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                String linea;

                while ((linea = bufferedReader.readLine()) != null) {

                    String ca[] = linea.split("-");

                    if (ca.length > 0) {
                        //Toast.makeText(getBaseContext(), ca[0].toString(), Toast.LENGTH_LONG).show();
                        ultimoId = Integer.parseInt(ca[0].toString());
                        ultimoId++;
                    } else {
                        //Toast.makeText(getBaseContext(), "No hay datos", Toast.LENGTH_LONG).show();
                        ultimoId = 0;
                    }

                }

                bufferedReader.close();
                //Toast.makeText(getBaseContext(), sb.toString(), Toast.LENGTH_SHORT).show();
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
                Toast.makeText(getBaseContext(), "Solo lectura", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(), "No es posible acceder a la Micro SD", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return String.valueOf(ultimoId);
    }

    public void back(View v) {
        finish();
        //Intent intent=new Intent(this,AgregarActivity.class);
        //startActivity(intent);
    }

}
