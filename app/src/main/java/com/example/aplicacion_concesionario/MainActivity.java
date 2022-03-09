package com.example.aplicacion_concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText jetusuario,jetclave;
    Button jbtingresar,jbtcancelar,jbtregistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        jetusuario=findViewById(R.id.etusuario);
        jetclave=findViewById(R.id.etclave);
        jbtingresar=findViewById(R.id.btingresar);
        jbtcancelar=findViewById(R.id.btcancelar);
        jbtregistrarse=findViewById(R.id.btregistrarse);
    }

    public void Cancelar(View view){
        jetusuario.setText("");
        jetclave.setText("");
        jetusuario.requestFocus();
    }

    public void Ingresar(View view){
        String usuario, contraseña;
        usuario=jetusuario.getText().toString();
        contraseña=jetclave.getText().toString();
        Conexion_Sqlite admin=new Conexion_Sqlite(this,"concesionario.db",null,1);
        SQLiteDatabase db=admin.getReadableDatabase();
        Cursor fila=db.rawQuery("select * from TblCliente where usuario='" + usuario + "'",null);
        if (fila.moveToNext()){
            if(contraseña.equals(fila.getString(3))){
                Intent menu=new Intent(this,MenuActivity.class);
                startActivity(menu);
            }else{
                Toast.makeText(this, "Error en la contraseña", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "El usuario no fue encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    public void Registrarse(View view){
        Intent intregistrarse=new Intent(this,RegistrarseActivity.class);
        startActivity(intregistrarse);

        }

    }

