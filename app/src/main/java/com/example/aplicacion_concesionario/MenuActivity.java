package com.example.aplicacion_concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button jbtvehiculo, jbtfactura, jbtlistar, jbtusuario, jbtsalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        jbtvehiculo = findViewById(R.id.btvehiculo);
        jbtfactura = findViewById(R.id.btfactura);
        jbtlistar=findViewById(R.id.btlistar);
        jbtusuario=findViewById(R.id.btusuario);
        jbtsalir=findViewById(R.id.btsalir);

    }

    public void abrirVehiculos(View view){
        Intent vehiculo = new Intent(this, VehiculoActivity.class);
        startActivity(vehiculo);
    }

    public void abrirFactura(View view){
        Intent factura = new Intent(this, FacturaActivity.class);
        startActivity(factura);
    }

    public void listarVehiculos(View view){
        Intent listar = new Intent(this,MostrarActivity.class);
        startActivity(listar);
    }

    public void abrirUsuario(View view){
        Intent usuario = new Intent(this, RegistrarseActivity.class);
        startActivity(usuario);
    }

    public void salir(View view){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

}