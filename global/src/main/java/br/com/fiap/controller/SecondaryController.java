package br.com.fiap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fiap.App;
import br.com.fiap.dao.ClienteDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import br.com.fiap.model.Cliente;
import br.com.fiap.util.ConnectionFactory;

public class SecondaryController {

    @FXML public TextField txtNomeCliente;
    @FXML public TextField txtEmailCliente;
    @FXML public TextField txtEnderecoCliente;
    @FXML public TextField txtTelefoneCliente;
    @FXML public TextField txtCpfCliente;
    @FXML public TextField txtLoginCliente;
    @FXML public TextField txtSenhaCliente;

    @FXML public PasswordField senhaCliente;
    @FXML public TextField txtLogin2Cliente;


    private Cliente carregarClienteDoFormulario(){
        String nome = txtNomeCliente.getText();
        String email = txtEmailCliente.getText();
        String endereco = txtEnderecoCliente.getText();
        String telefone = txtTelefoneCliente.getText();
        String cpf = txtCpfCliente.getText();
        String login = txtLoginCliente.getText();
        String senha = txtSenhaCliente.getText();
    

        return new Cliente(nome, telefone, email, endereco, cpf, login, senha);
    }



    public void salvar(){
        var cliente = carregarClienteDoFormulario();
  
        try {
            ClienteDao dao = new  ClienteDao();
  
            dao.inserir(cliente);
  
            mostrarAlerta("Cliente cadatrado com sucesso");
            limparFormulario();
  
        } catch (SQLException e) {
            mostrarAlerta("Erro de conexao com BD " + e.getMessage());
        } catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
        
    }



    private void limparFormulario(){
        txtNomeCliente.setText("");
        txtEmailCliente.setText("");
        txtEnderecoCliente.setText("");
        txtTelefoneCliente.setText("");
        txtCpfCliente.setText("");
        txtLoginCliente.setText("");
        txtSenhaCliente.setText("");
    }

    private void mostrarAlerta(String mensagem) {
        var alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.show();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }




    @FXML
    private void logarTeste()throws IOException{
        String senha=senhaCliente.getText();
        
        try{
            String sql="Select login_cliente,senha_cliente from tb_cliente where login_cliente='"+txtLogin2Cliente.getText()+"'";
            
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            stmt= con.prepareStatement(sql);
            
            ResultSet rs =stmt.executeQuery(sql);


            while(rs.next()){
                if(senha.equals(rs.getString("senha_cliente"))){
                    mostrarAlerta("Senha e login correto");;
                    App.setRoot("quinto");
                
                }

                else {
                    mostrarAlerta("Senha e login errado");;
                
                }
            }
        }
        catch(Exception e){
        mostrarAlerta("erro na conexão do BD " + e.getMessage());
        }
    }


}