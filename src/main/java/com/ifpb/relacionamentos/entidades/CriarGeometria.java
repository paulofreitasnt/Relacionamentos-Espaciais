package com.ifpb.relacionamentos.entidades;

import com.ifpb.relacionamentos.banco.ConFactory;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarGeometria {

    private String nome;
    private String tipo;
    private Connection conexao;
    
    public CriarGeometria(String nome, String tipo) throws ClassNotFoundException, SQLException{
        this.nome = nome;
        this.tipo = tipo;
        this.conexao = new ConFactory().getConnection();
    }
    
    private String criarquery(){
        
        String query = "SELECT ST_AsEWKT (geom) FROM ";
        
        switch(tipo){
            case "Região":
                query += "regiao ";
                break;
            case "Estado":
                query += "estado ";
                break;
            case "Mesorregião":
                query += "mesorregiao ";
                break;
            case "Microrregião":
                query += "microrregiao ";
                break;
            case "Município":
                query += "municipio ";
                break;
        }
        
        query += "WHERE nome ilike '"+nome+"'";
        
        return query;
        
    }
    
    public Geometry getGeometria() throws SQLException, ParseException{
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery(criarquery());
        String ewkt = "";
        while(rs.next()){
            ewkt = rs.getString(1);
        }
        
        Geometry geom = new WKTReader().read(ewkt);
        
        return geom;
        
    }
    
}
