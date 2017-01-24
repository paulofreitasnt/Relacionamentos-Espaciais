package com.ifpb.relacionamentos.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {

    private final String url;
    private final String usuario;
    private final String senha;
    
    public ConFactory() throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        url = "jdbc:postgresql://localhost:5432/IBGE";
        usuario = "postgres";
        senha = "postgres";
    }
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, usuario, senha);
    }
    
}