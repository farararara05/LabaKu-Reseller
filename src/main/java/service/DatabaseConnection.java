/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ACER
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_labaku";
    private static final String USER = "root"; 
    private static final String PASSWORD = "f4raSQL"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // calling driver jdbc mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Koneksi database berhasil!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
        }
        return connection;
    }
}