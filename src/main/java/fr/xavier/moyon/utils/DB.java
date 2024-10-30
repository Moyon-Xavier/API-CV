package fr.xavier.moyon.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("org.mariadb.jdbc.Driver");
        String url = "jdbc:mariadb://localhost:3306/CV";
        String user = "root";
        String mdp = "";
        return DriverManager.getConnection(url, user, mdp);
    }

    public static void main(String[] args) {
        try {
            Connection con = DB.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * From period");
            System.out.println("Hello");
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
            con.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
