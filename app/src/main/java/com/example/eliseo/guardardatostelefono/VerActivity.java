package com.example.eliseo.guardardatostelefono;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VerActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        listView = (ListView) findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_NONE);

        listado=new ArrayList<>();
        listado= leer();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, listado);

        listView.setAdapter(adaptador);
    }

    public ArrayList leer() {
        File extDir = Environment.getExternalStorageDirectory();
        String FILE_NAME = extDir.getAbsolutePath() + "/Base.txt";
        int ultimoId = 0;
        ArrayList<String> lista=new ArrayList<>();
        int indice=0;
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
                        lista.add(ca[1] + "\n" + ca[2]);

                    } else {
                        //Toast.makeText(getBaseContext(), "No hay datos", Toast.LENGTH_LONG).show();
                        //ultimoId = 0;
                        lista.add("No hay\nNada");
                    }

                }

                bufferedReader.close();
                //Toast.makeText(getBaseContext(), sb.toString(), Toast.LENGTH_LONG).show();
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
                Toast.makeText(getBaseContext(), "Solo lectura", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(), "No es posible acceder a la Micro SD", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return lista;
    }
    public void back(View v) {
        finish();

    }


}
