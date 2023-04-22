package br.com.fiap.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.fiap.App;

public class ConnectionFactory {

    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private static final String USER = "RM95725";
    private static final String PASS = "121202";

    
    public static Connection getConnection() throws SQLException, IOException{
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
