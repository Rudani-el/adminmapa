package com.example.mimapa4;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBdd extends AppCompatActivity {
    private static final String DRIVER = "oracle.odbc.driver.OracleDriver";
    private  static final String URL = "odbc:oracle:thin:@10.141.50.185:1521:milo";
    private static final String USERNAME = "maritimo";
    private static final String PASSWORD = "maritimo";
    private Connection conexion;

    public ConexionBdd(){
        Connection conexion = null;

        this.conexion=conexion;


    }

    public void connecteddb(View vista){
        try {
            Class.forName(DRIVER);
            this.conexion= DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Toast.makeText(this, "CONECTADO", Toast.LENGTH_LONG).show();
            Statement statement=conexion.createStatement();
            StringBuffer stringBuffer=new StringBuffer();
            ResultSet resultSet=statement.executeQuery("select * from maritimo.ge_usuarios");
            while(resultSet.next()){
                stringBuffer.append(resultSet.getString(1) + "\n");

            }

            Toast.makeText(this, " " + stringBuffer.toString(), Toast.LENGTH_LONG).show();
            conexion.close();

        } catch (ClassNotFoundException e) {
            Toast.makeText(this, " " + e.toString(), Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            Toast.makeText(this, " " + e.toString(), Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }



}
