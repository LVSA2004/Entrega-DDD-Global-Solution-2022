package br.com.fiap.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import br.com.fiap.App;
import br.com.fiap.dao.CarroDao;
import br.com.fiap.dao.ClienteDao;
import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.model.Carro;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Funcionario;
import br.com.fiap.util.ConnectionFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuartoController implements Initializable{

    @FXML private TextField txtNome;
    @FXML private TextField txtModelo;
    @FXML private TextField txtAno;
    @FXML private TextField txtPlaca;
    @FXML private Button btnAlugado;
    @FXML private Button btnDisponivel;
    @FXML private Button btnAlugado2;
    @FXML private Button btnDisponivel2;
    @FXML private TextField txtNomeUpdate;

    private String alugado = "nulo";

    @FXML private TableView<Carro> tabelaCarro;
    @FXML private TableColumn<Carro, String> colunaNomeCarro;
    @FXML private TableColumn<Carro, String> colunaModeloCarro;
    @FXML private TableColumn<Carro, Integer> colunaAnoCarro;
    @FXML private TableColumn<Carro, String> colunaPlacaCarro;
    @FXML private TableColumn<Carro, String> colunaAluguelCarro;

    
    @FXML private TableView<Cliente> tabelaCliente;
    @FXML private TableColumn<Cliente, String> colunaNomeCliente;
    @FXML private TableColumn<Cliente, String> colunaEmailCliente;
    @FXML private TableColumn<Cliente, String> colunaTelefoneCliente;
    @FXML private TableColumn<Cliente, String> colunaEnderecoCliente;
    @FXML private TableColumn<Cliente, String> colunaCPFCliente;
    @FXML private TableColumn<Cliente, String> colunaLoginCliente;
    @FXML private TableColumn<Cliente, String> colunaSenhaCliente;

    @FXML private TableView<Funcionario> tabelaFuncionario;
    @FXML private TableColumn<Funcionario, String> colunaNomeFuncionario;
    @FXML private TableColumn<Funcionario, String> colunaEmailFuncionario;
    @FXML private TableColumn<Funcionario, String> colunaTelefoneFuncionario;
    @FXML private TableColumn<Funcionario, String> colunaEnderecoFuncionario;
    @FXML private TableColumn<Funcionario, String> colunaLoginFuncionario;
    @FXML private TableColumn<Funcionario, String> colunaSenhaFuncionario;


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


    @FXML
    private void colocarAlugado(){
        btnAlugado.setDisable(true);
        btnDisponivel.setDisable(false);
        alugado = "Alugado";
    }

    @FXML
    private void colocarDisponivel(){
        btnAlugado.setDisable(false);
        btnDisponivel.setDisable(true);
        alugado = "Disponivel";
        
    }

    @FXML
    private void colocarAlugado2(){
        btnAlugado2.setDisable(true);
        btnDisponivel2.setDisable(false);
        alugado = "Alugado";
    }

    @FXML
    private void colocarDisponivel2(){
        btnAlugado2.setDisable(false);
        btnDisponivel2.setDisable(true);
        alugado = "Disponivel";
        
     
    }

    private Carro carregarCarroDoFormulario(){
        String nome = txtNome.getText();
        String modelo = txtModelo.getText();
        int ano = Integer.valueOf(txtAno.getText());
        String placa = txtPlaca.getText();
        String aluguel= alugado;

        return new Carro(nome, modelo, ano, placa, aluguel);
    }


    public void salvar(){
        var carro = carregarCarroDoFormulario();
  
        try {
            CarroDao dao = new  CarroDao();
  
            dao.inserir(carro);
            carregarDadosCarro();
  
            mostrarAlerta("carro cadatrado com sucesso");
            limparFormulario();
  
        } catch (SQLException e) {
            mostrarAlerta("Erro de conexao com BD " + e.getMessage());
        } catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
        
    }

    private void limparFormulario(){
        txtNome.setText("");
        txtModelo.setText("");
        txtAno.setText("");
        txtPlaca.setText("");
    }



    private void carregarDadosCarro(){
        tabelaCarro.getItems().clear();
        try{
            var listaCarro = new CarroDao().buscarTodosCarros();
            tabelaCarro.getItems().addAll(listaCarro);
        } catch (SQLException e) {
            mostrarAlerta(e.getMessage());
            e.printStackTrace();
        }  catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
    }

    private void carregarDadosCliente(){
        tabelaCliente.getItems().clear();
        try{
            var listaCliente = new ClienteDao().buscarTodosClientes();
            tabelaCliente.getItems().addAll(listaCliente);
        } catch (SQLException e) {
            mostrarAlerta(e.getMessage());
            e.printStackTrace();
        }  catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
    }

    private void carregarDadosFuncionario(){
        tabelaFuncionario.getItems().clear();
        try{
            var listaFuncionario = new FuncionarioDao().buscarTodosFuncionario();
            tabelaFuncionario.getItems().addAll(listaFuncionario);
        } catch (SQLException e) {
            mostrarAlerta(e.getMessage());
            e.printStackTrace();
        }  catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
    }

    private void mostrarAlerta(String mensagem) {
        var alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.show();
    }

    @FXML
    private void updateTeste(){
        try{
            String sql="UPDATE TB_CARROS SET ALUGUEL_CARRO = '"+ alugado +"' WHERE NOME_CARRO = '"+txtNomeUpdate.getText()+"'";
            
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            stmt= con.prepareStatement(sql);
            
            ResultSet rs =stmt.executeQuery(sql);


            while(rs.next()){
                carregarDadosCarro();
            }
        }
        catch(Exception e){
        mostrarAlerta("Mudança feita com sucesso");
        }
        txtNomeUpdate.setText("");
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colunaNomeCarro.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaModeloCarro.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colunaAnoCarro.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colunaPlacaCarro.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colunaAluguelCarro.setCellValueFactory(new PropertyValueFactory<>("aluguel"));

        carregarDadosCarro();

        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmailCliente.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaEnderecoCliente.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaCPFCliente.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaLoginCliente.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunaSenhaCliente.setCellValueFactory(new PropertyValueFactory<>("senha"));

        carregarDadosCliente();

        colunaNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEmailFuncionario.setCellValueFactory(new PropertyValueFactory<>("email"));
        colunaTelefoneFuncionario.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaEnderecoFuncionario.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colunaLoginFuncionario.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunaSenhaFuncionario.setCellValueFactory(new PropertyValueFactory<>("senha"));

        carregarDadosFuncionario();

    }
}