package com.example.eliseo.guardardatostelefono;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irAdd(View v) {
        Intent intent=new Intent(this,AgregarActivity.class);
        startActivity(intent);
    }
    public void ver(View v) {
        Intent intent=new Intent(this,VerActivity.class);
        startActivity(intent);
    }

}
