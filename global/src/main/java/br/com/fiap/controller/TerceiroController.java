package br.com.fiap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fiap.App;
import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.model.Funcionario;
import br.com.fiap.util.ConnectionFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class TerceiroController {

    @FXML public TextField txtNomeFuncionario;
    @FXML public TextField txtEmailFuncionario;
    @FXML public TextField txtEnderecoFuncionario;
    @FXML public TextField txtTelefoneFuncionario;
    @FXML public TextField txtLoginFuncionario;
    @FXML public TextField txtSenhaFuncionario;

    @FXML public PasswordField senhaFuncionario;
    @FXML public TextField txtLogin2Funcionario;


    private Funcionario carregarFuncionarioDoFormulario(){
        String nome = txtNomeFuncionario.getText();
        String email = txtEmailFuncionario.getText();
        String endereco = txtEnderecoFuncionario.getText();
        String telefone = txtTelefoneFuncionario.getText();
        String login = txtLoginFuncionario.getText();
        String senha = txtSenhaFuncionario.getText();
    
        


        return new Funcionario(nome, telefone, email, endereco, login, senha);
    }

    public void salvar(){
        var funcionario = carregarFuncionarioDoFormulario();
  
        try {
            FuncionarioDao dao = new  FuncionarioDao();
  
            dao.inserir(funcionario);
  
            mostrarAlerta("Funcionario cadatrado com sucesso");
            limparFormulario();
  
        } catch (SQLException e) {
            mostrarAlerta("Erro de conexao com BD " + e.getMessage());
        } catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
        
    }
    private void limparFormulario(){
        txtNomeFuncionario.setText("");
        txtEmailFuncionario.setText("");
        txtEnderecoFuncionario.setText("");
        txtTelefoneFuncionario.setText("");
        txtLoginFuncionario.setText("");
        txtSenhaFuncionario.setText("");

    }

    private void mostrarAlerta(String mensagem) {
        var alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.show();
    }

    @FXML
    private void logarTeste()throws IOException{
        String senha=senhaFuncionario.getText();
        
        try{
            String sql="Select login_funcionario,senha_funcionario from tb_funcionario where login_funcionario='"+txtLogin2Funcionario.getText()+"'";
            
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            stmt= con.prepareStatement(sql);
            
            ResultSet rs =stmt.executeQuery(sql);


            while(rs.next()){
                if(senha.equals(rs.getString("senha_funcionario"))){
                    mostrarAlerta("Senha e login correto");;
                    App.setRoot("quarto");
                
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



    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}