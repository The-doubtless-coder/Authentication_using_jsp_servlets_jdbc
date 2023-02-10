package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static Connection conn =null;
    public static Connection getConnection(){
        try{
            if(conn==null) {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bootlogin", "postgres", "root");
            }
            }
        catch (ClassNotFoundException | SQLException c){
            c.printStackTrace();
        }
        return conn;
    }
}
