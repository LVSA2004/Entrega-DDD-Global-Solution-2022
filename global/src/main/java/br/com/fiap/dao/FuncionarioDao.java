package br.com.fiap.dao;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.model.Funcionario;
import br.com.fiap.util.ConnectionFactory;

public class FuncionarioDao{

    public void inserir(Funcionario funcionario) throws SQLException, IOException {
        Connection con = ConnectionFactory.getConnection();
        
        String sql = "INSERT INTO TB_FUNCIONARIO ( nome_funcionario, email_funcionario, telefone_funcionario, endereco_funcionario, login_funcionario, senha_funcionario)"+
        "VALUES ( ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getEmail());
        stmt.setString(3, funcionario.getTelefone());
        stmt.setString(4, funcionario.getEndereco());
        stmt.setString(5, funcionario.getLogin());
        stmt.setString(6, funcionario.getSenha());

        
        stmt.execute();
        con.close();
}

public List<Funcionario> buscarTodosFuncionario() throws SQLException, IOException {
    List<Funcionario> listaFuncionario = new ArrayList<>();
    
    Connection con = ConnectionFactory.getConnection();
    Statement stmt = con.createStatement();
    String sql = "SELECT * FROM TB_FUNCIONARIO";
    ResultSet rs = stmt.executeQuery(sql);

    while(rs.next()){
       
        String nome = rs.getString("NOME_FUNCIONARIO");
        String email = rs.getString("EMAIL_FUNCIONARIO");
        String telefone = rs.getString("TELEFONE_FUNCIONARIO");
        String endereco = rs.getString("ENDERECO_FUNCIONARIO");
        String login = rs.getString("LOGIN_FUNCIONARIO");
        String  senha= rs.getString("SENHA_FUNCIONARIO");
        
       
        
        
        Funcionario funcionario= new Funcionario(nome, email, telefone, endereco, login, senha);
        listaFuncionario.add(funcionario);
    }

    con.close();
    return listaFuncionario;
}


}
