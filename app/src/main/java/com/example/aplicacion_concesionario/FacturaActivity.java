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

import java.util.Date;

public class FacturaActivity extends AppCompatActivity {

    EditText jetcodigo, jetfecha, jetidentificacion, jetplaca;
    Button jbtguardar, jbtconsultar, jbtanular, jbtcancelar, jbtregresar;
    long sw, resp;
    String  codigo,identificacion, placa, fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        jetcodigo= findViewById(R.id.etcodigo);
        jetfecha=findViewById(R.id.etfecha);
        jetidentificacion=findViewById(R.id.etidentificacion);
        jetplaca=findViewById(R.id.etplaca);
        jbtguardar=findViewById(R.id.btguardar);
        jbtconsultar=findViewById(R.id.btconsultar);
        jbtanular=findViewById(R.id.btanular);
        jbtcancelar=findViewById(R.id.btcancelar);
        jbtregresar=findViewById(R.id.btregresar);
        sw=0;
    }

    public void Guardar(View view){

        placa=jetplaca.getText().toString();
        codigo=jetcodigo.getText().toString();
        identificacion=jetidentificacion.getText().toString();
        fecha=jetfecha.getText().toString();

        if(placa.isEmpty() || codigo.isEmpty() || identificacion.isEmpty() || fecha.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetcodigo.requestFocus();
        }else{
            Conexion_Sqlite admin=new Conexion_Sqlite(this,"concesionario.db",null,1);
            SQLiteDatabase leer=admin.getReadableDatabase();
            Cursor fila = leer.rawQuery("select * from TblFactura where codFactura='"+codigo+"'",null);

            Conexion_Sqlite admin2=new Conexion_Sqlite(this,"concesionario.db",null,1);
            SQLiteDatabase escribir=admin2.getWritableDatabase();
            ContentValues registro=new ContentValues();

            registro.put("codFactura",codigo);
            registro.put("fecha",fecha);
            registro.put("idcliente",identificacion);
            registro.put("placa",placa);

            ConsultarFactura();

            boolean validacion1=false, validacion2=false;
            if(sw==1) {
                sw = 0;
                resp=escribir.update("TblFactura", registro, "codFactura='"+codigo+"'", null);
            }else{
                if (identificacion.equals(fila.getString(1))) {
                    validacion1 = true;
                }
                if ("si".equals(fila.getString(4))) {
                    validacion2 = true;
                }
                if (validacion1 == true) {
                    if (validacion2 == true) {
                        resp=escribir.insert("TblFactura",null,registro);
                    } else {
                        Toast.makeText(this, "El vehiculo no esta disponible", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (validacion2 == true) {
                        Toast.makeText(this, "El usuario no se encuentra registrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "El vehiculo no esta disponible y el usuario no se encuentra registrado", Toast.LENGTH_SHORT).show();
                    }
                }
            }//sw

            if (resp > 0){
                Limpiar_campos();
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Error en guardar el registro", Toast.LENGTH_SHORT).show();
            }


        }//esta llenos los campos
    }//metodo

    public void Consulta_factura(View view){
        ConsultarFactura();
    }

    public void ConsultarFactura(){

        codigo=jetcodigo.getText().toString();
        if (codigo.isEmpty()){
            Toast.makeText(this, "El codigo de la factura es requerido para buscar", Toast.LENGTH_SHORT).show();
            jetcodigo.requestFocus();
        }
        else{
            Conexion_Sqlite admin=new Conexion_Sqlite(this,"concesionario.db",null,1);
            SQLiteDatabase db=admin.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from TblFactura where codFactura='" + codigo + "'",null);
            if (fila.moveToNext()){
                sw=1;
                jetfecha.setText(fila.getString(1));
                jetidentificacion.setText(fila.getString(2));
                jetplaca.setText(fila.getString(3));
            }
            else{
                Toast.makeText(this, "Registro no hallado", Toast.LENGTH_SHORT).show();
            }
            //db.close();
        }
    }

    public void AnularFactura(View view){
        ConsultarFactura();
        if(sw==1){
            Conexion_Sqlite admin = new Conexion_Sqlite(this,"concesionario.db",null,1);
            SQLiteDatabase db= admin.getWritableDatabase();
            ContentValues registro= new ContentValues();
            registro.put("codFactura",codigo);
            registro.put("activo", "no");
            resp=db.update("TblVehiculo",registro, "placa='"+placa+"'",null);
            if(resp>0){
                Toast.makeText(this, "Registro Anulado", Toast.LENGTH_SHORT).show();
                Limpiar_campos();
            }else{
                Toast.makeText(this, "Error al anular el registro", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "La placa no esta registrada", Toast.LENGTH_SHORT).show();
        }
    }

    public void Limpiar_campos(){
        sw=0;
        jetplaca.setText("");
        jetfecha.setText("");
        jetidentificacion.setText("");
        jetcodigo.setText("");
        jetcodigo.requestFocus();
    }

    public void Regresar(View view){
        Intent main = new Intent(this, MenuActivity.class);
        startActivity(main);
    }


}