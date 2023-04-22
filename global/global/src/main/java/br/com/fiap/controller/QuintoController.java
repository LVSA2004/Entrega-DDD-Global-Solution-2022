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
import br.com.fiap.model.Carro;
import br.com.fiap.util.ConnectionFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class QuintoController implements Initializable {

    @FXML private TextField txtNomeCarro;
    String alugado;
    
    @FXML private TableView<Carro> tabelaCarro;
    @FXML private TableColumn<Carro, String> colunaNomeCarro;
    @FXML private TableColumn<Carro, String> colunaModeloCarro;
    @FXML private TableColumn<Carro, Integer> colunaAnoCarro;
    @FXML private TableColumn<Carro, String> colunaPlacaCarro;
    @FXML private TableColumn<Carro, String> colunaAluguelCarro;


    private void carregarDadosCarro(){
        tabelaCarro.getItems().clear();
        try{
            var listaCarro = new CarroDao().buscarTodosCarrosDisponiveis();
            tabelaCarro.getItems().addAll(listaCarro);
        } catch (SQLException e) {
            mostrarAlerta(e.getMessage());
            e.printStackTrace();
        }  catch (IOException e) {
            mostrarAlerta("Erro ao obter propriedades de conexão. Verifique arquivo application.properties. " + e.getMessage());
        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }



    @FXML
    private void alugar(){
        alugado ="Alugado";
        try{
            String sql="UPDATE TB_CARROS SET ALUGUEL_CARRO = '"+ alugado +"' WHERE NOME_CARRO = '"+txtNomeCarro.getText()+"'";
            
            Connection con = ConnectionFactory.getConnection();
            Statement stmt = con.createStatement();
            stmt= con.prepareStatement(sql);
            
            ResultSet rs =stmt.executeQuery(sql);


            while(rs.next()){
                carregarDadosCarro();
            }
        }
        catch(Exception e){
        mostrarAlerta("Aluguel feito com sucesso");
        }
        txtNomeCarro.setText("");
    }

    private void mostrarAlerta(String mensagem) {
        var alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(mensagem);
        alert.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colunaNomeCarro.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaModeloCarro.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colunaAnoCarro.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colunaPlacaCarro.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colunaAluguelCarro.setCellValueFactory(new PropertyValueFactory<>("aluguel"));

        carregarDadosCarro();
        
    }

}
