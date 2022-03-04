package com.example.aplicacion_concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class VehiculoActivity extends AppCompatActivity {

    EditText jetplaca, jetmarca, jetmodelo, jetcosto, jetcolor;
    Button jbtguardar, jbtconsultar, jbtanular, jbtingresar, jbtcancelar, jbtregresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo);
        getSupportActionBar().hide();

        jetplaca=findViewById(R.id.etplaca);
        jetmarca=findViewById(R.id.etmarca);
        jetmodelo=findViewById(R.id.etmodelo);
        jetcosto=findViewById(R.id.etcosto);
        jetcolor=findViewById(R.id.etcolor);

        jbtguardar=findViewById(R.id.btguardar);
        jbtanular=findViewById(R.id.btanular);
        jbtcancelar=findViewById(R.id.btcancelar);
        jbtconsultar=findViewById(R.id.btconsultar);
        jbtingresar=findViewById(R.id.btingresar);
        jbtregresar=findViewById(R.id.btregresar);
    }
}