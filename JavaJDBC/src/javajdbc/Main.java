/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author human
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {

        // Load JDBC (MySQL) driver class.
        Class.forName(com.mysql.jdbc.Driver.class.getName());
//        Class.forName("com.mysql.jdbc.Driver");

        String hostname = "192.168.1.99";
        int hostport = 3306;
        String database = "test";
        String username = "student";
        String password = "12345";

        String connectionString = String.format("jdbc:mysql://%s:%d/%s?useSSL=true", hostname, hostport, database);

        try (Connection conn = DriverManager.getConnection(connectionString, username, password)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES");

            System.out.println("+----------------------+");
            System.out.println("| - TABLES ----------- |");
            System.out.println("+----------------------+");

            int i = 1;
            
            while (rs.next()) {
                String tableName = rs.getString(1); // first column - 1, second - 2

                System.out.printf("| %02d. %-16s |\n", i++, tableName);
            }
            
            System.out.println("+----------------------+");

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
