package br.com.fiap.dao;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Carro;
import br.com.fiap.util.ConnectionFactory;

public class CarroDao{

    public void inserir(Carro carro) throws SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        
        String sql = "INSERT INTO TB_CARROS (nome_carro, modelo_carro, ano_carro, placa_carro,aluguel_carro)"+
        "VALUES ( ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1, carro.getNome());
        stmt.setString(2, carro.getModelo());
        stmt.setInt(3, carro.getAno());
        stmt.setString(4, carro.getPlaca());
        stmt.setString(5, carro.getAluguel());
       

        
        stmt.execute();
        con.close();
}

public List<Carro> buscarTodosCarros() throws SQLException, IOException {
    List<Carro> listaCarro = new ArrayList<>();
    
    Connection con = ConnectionFactory.getConnection();
    Statement stmt = con.createStatement();
    String sql = "SELECT * FROM TB_CARROS";
    ResultSet rs = stmt.executeQuery(sql);

    while(rs.next()){
       
        String nome = rs.getString("NOME_CARRO");
        String modelo = rs.getString("MODELO_CARRO");
        int ano = rs.getInt("ANO_CARRO");
        String placa = rs.getString("PLACA_CARRO");
        String aluguel = rs.getString("ALUGUEL_CARRO");
       
       
        
        
        Carro carro= new Carro(nome, modelo, ano, placa, aluguel);
        listaCarro.add(carro);
    }

    con.close();
    return listaCarro;
}

public List<Carro> buscarTodosCarrosDisponiveis() throws SQLException, IOException {
    List<Carro> listaCarro = new ArrayList<>();
    
    Connection con = ConnectionFactory.getConnection();
    Statement stmt = con.createStatement();
    String sql = "SELECT * FROM TB_CARROS WHERE ALUGUEL_CARRO = 'Disponivel'";
    ResultSet rs = stmt.executeQuery(sql);

    while(rs.next()){
       
        String nome = rs.getString("NOME_CARRO");
        String modelo = rs.getString("MODELO_CARRO");
        int ano = rs.getInt("ANO_CARRO");
        String placa = rs.getString("PLACA_CARRO");
        String aluguel = rs.getString("ALUGUEL_CARRO");
       
       
        
        
        Carro carro= new Carro(nome, modelo, ano, placa, aluguel);
        listaCarro.add(carro);
    }

    con.close();
    return listaCarro;
}


}
