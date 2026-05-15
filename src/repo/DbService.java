package repo;
import java.sql.*;
import service.Book;
public class DbService {
    private DbService () {};
    public static void addBookToDatabase(Book book, Database db) {
        if (!isInDatabase(book, db)) { //jezeli nie ma w bazie danych - dodaj
            try {
                String sql = "INSERT INTO books(title, author, year, value) VALUES(?, ?, ?, ?)";
                PreparedStatement ps = db.getConn().prepareStatement(sql);
                ps.setString(1, book.title);
                ps.setString(2, book.author);
                ps.setInt(3, book.year);
                ps.setInt(4, book.value);
                ps.executeUpdate();
                ps.close();
            }
            catch (SQLException e) {System.out.println(e);}
        }
        else { // jezeli jest w bazie danych - powiedz
            System.out.println("istnieje w bazie danych");
        }
    } 

        
    public static void removeBookFromDatabase(String title, Database db) {
            try {
                String sql = "DELETE FROM books WHERE title = ?";
                PreparedStatement ps = db.getConn().prepareStatement(sql);
                ps.setString(1, title);
                System.out.printf("usuwanie %s z bazy danych\n",title);
                ps.executeUpdate();
                ps.close();
        }
            catch (SQLException e) {System.out.println(e);}
    }
    public static boolean isInDatabase(Book book, Database db) {
        try {
            String sql = "SELECT EXISTS (SELECT 1 FROM books WHERE title = ?)";
            PreparedStatement ps = db.getConn().prepareStatement(sql);
            ps.setString(1, book.title);
            ResultSet rs = ps.executeQuery();
            boolean exists = false;
            if (rs.next()) {
               exists = rs.getBoolean(1);
            }
            rs.close();
            ps.close();
            return exists;
            
            
        }
        catch (SQLException e) {System.out.println(e);return false;}
    }
    public static void infoFromDatabase(String titleToGet, Database db) {
            try {
                String sql = "SELECT * FROM books WHERE title = ?";
                PreparedStatement ps = db.getConn().prepareStatement(sql);
                ps.setString(1, titleToGet);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    int year = rs.getInt("year");
                    int value = rs.getInt("value");
                    System.out.printf("%s-%s-%d-%d\n",title,author,year,value);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {System.out.println("nie ma");} 
}
    public static Book getBookByTitle(String titleToFind, Database db) {
        try {
            String sql = "SELECT * FROM books WHERE title = ?";
            PreparedStatement ps = db.getConn().prepareStatement(sql);
            ps.setString(1, titleToFind);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                int year = rs.getInt("year");
                int value = rs.getInt("value");
                Book book = new Book(title, author, year, value);
                ps.close();
                rs.close();
                return book;
            }
            
        } catch (SQLException e) {System.out.println("nie ma takiej ksiazki");return null;}
        return null;
    }
    public static void updateBook(Book book, Database db) {
        try {
            String sql = "UPDATE books SET title = ?, author = ?, year = ?, value = ? WHERE title = ? ";
            PreparedStatement ps = db.getConn().prepareStatement(sql);
            ps.setString(1, book.title);
            ps.setString(2, book.author);
            ps.setInt(3, book.year);
            ps.setInt(4,book.value);
            ps.setString(5, book.title);
            ps.executeUpdate();
            ps.close();
            System.out.println("zaaktualizowano dane dla: " + book);
        } catch (SQLException e) {System.out.println(e);
        }
    }
}
