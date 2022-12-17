package utilies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    //connection
    //statement
    //con ve statement kapatma
    private static Connection connection;
    private static Statement statement;

    //1.adım : connection olustur
    public static Connection connectionOlustur(String hostname, String databaseismi, String username, String password){

        //1. adım : drivera kaydol
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2. adım : connection olustur
        //url syntax:jdbc:postgresql://hostname:portnumber/databaseismi
        String url="jdbc:postgresql://"+hostname+":5432/"+databaseismi;

        try {
            connection= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;

    }

    public static Statement statementOlustur(){
        try {
            statement=connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    public static void connectionStatementKapat(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}