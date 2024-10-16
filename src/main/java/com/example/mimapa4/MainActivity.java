package com.example.mimapa4;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mapa;
    private EditText user, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText) findViewById(R.id.edusuario);
        password=(EditText) findViewById(R.id.edclave);
        String val1=user.getText().toString();
        String val2=password.getText().toString();
        mapa=(Button) findViewById(R.id.btnabrirmapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo internet=cm.getActiveNetworkInfo();
                    if (internet!=null){
                        /*
                        do{
                            if (val1.equals("Admin") && val2.equals("123")){

                            }
                        }while(val1!=null && val2!= null);
                        */

                        Intent i=new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(i);

                    }else{

                        Toast.makeText(MainActivity.this, "SIN ACCESO A INTERNET, VUELVA A INTERTAR DE NUEVO, ADICIONAL ENCIENDA EL GPS", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.toString();
                }


            }
        });
    }
/*
    public void iniciosesion(){
        user=(EditText) findViewById(R.id.edusuario);
        password=(EditText) findViewById(R.id.edclave);
        String val1=user.getText().toString();
        String val2=password.getText().toString();

        if (val1.toString().equals("Admin")&& val2.toString().equals("123")){
            System.out.println("Bienvenido al sistema SIGMAP R23.0-BR");
        }else {
            Toast.makeText(this, "USUARIO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_SHORT).show();
        }

    }


    public void mandarmapa(){
        Intent i=new Intent(MainActivity.this, MapsActivity.class);
        startActivity(i);

    }
    ConnectivityManager conma=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo inter=conma.getActiveNetworkInfo();
                if (inter!=null && inter.isConnectedOrConnecting()){
                    getUbicacion();
                    Toast.makeText(this, "HABILITANDO", Toast.LENGTH_SHORT).show();

                }else {
                    //mMap.setMyLocationEnabled(false);
                    Toast.makeText(this, "INTENTE DE NUEVO", Toast.LENGTH_SHORT).show();

                }

u=(EditText) findViewById(R.id.eduser);
                pass=(EditText) findViewById(R.id.edpass);


                String val1=u.getText().toString();
                String val2=pass.getText().toString();


                if (val1.equals(conectar.getString(Integer.parseInt("Admin"))) && val2.equals(conectar.getString(Integer.parseInt("12345")))){

                    Intent i = (Intent) new android.content.Intent(MainActivity.this, SecondActivity.class);

                    startActivity( i);

                    Toast.makeText(conectar, "BIEN", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(this, "USUARIO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_LONG).show();
                }


*/

}