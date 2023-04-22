package br.com.fiap.dao;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Cliente;
import br.com.fiap.util.ConnectionFactory;

public class ClienteDao{

    public void inserir(Cliente cliente) throws SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        
        String sql = "INSERT INTO TB_CLIENTE (nome_cliente, email_cliente, telefone_cliente, endereco_cliente, cpf_cliente, login_cliente, senha_cliente)"+
        "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getTelefone());
        stmt.setString(4, cliente.getEndereco());
        stmt.setString(5, cliente.getCpf());
        stmt.setString(6, cliente.getLogin());
        stmt.setString(7, cliente.getSenha());

        
        stmt.execute();
        con.close();
}

public List<Cliente> buscarTodosClientes() throws SQLException, IOException {
    List<Cliente> listaCliente = new ArrayList<>();
    
    Connection con = ConnectionFactory.getConnection();
    Statement stmt = con.createStatement();
    String sql = "SELECT * FROM TB_CLIENTE";
    ResultSet rs = stmt.executeQuery(sql);

    while(rs.next()){
       
        String nome = rs.getString("NOME_CLIENTE");
        String email = rs.getString("EMAIL_CLIENTE");
        String telefone = rs.getString("TELEFONE_CLIENTE");
        String endereco = rs.getString("ENDERECO_CLIENTE");
        String cpf = rs.getString("CPF_CLIENTE");
        String login = rs.getString("LOGIN_CLIENTE");
        String  senha= rs.getString("SENHA_CLIENTE");
        
       
        
        
        Cliente cliente= new Cliente(nome, email, telefone, endereco, cpf, login, senha);
        listaCliente.add(cliente);
    }

    con.close();
    return listaCliente;
}


}
