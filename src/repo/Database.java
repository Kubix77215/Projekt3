package repo;
import java.sql.*;
public class Database {
    private boolean isConnected = false;
    private Connection actualConnection;
    public Database() {
        this.isConnected = false;
    }
    public Database(String name) {  
        this.isConnected = false;
        if (name.equals("booksDB")) {this.connectBooksDB();}
    }
    public Connection booksDB() throws SQLException {
        try {
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/mydatabase",
                "postgres",
                "ZAQ!2wsx"
        );
        return conn;
        }
        catch (SQLException e) {System.out.println(e);return null;}
    }
    public void connectBooksDB() {
        try {
            Connection conn = booksDB();
            System.out.println("connected to " + conn);
            isConnected = true;
            actualConnection = conn;
        } 
        catch (SQLException e) {System.out.println(e);isConnected = false;actualConnection = null;}
    }

    public Connection getConn() {
        return this.actualConnection;
    }
}
