/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ACER
 */
import model.Reseller;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResellerDAO {

    // 1. CREATE ( add new reseller )
    public void insert(Reseller reseller) {
        String query = "INSERT INTO reseller (nama_reseller, produk_titipan, no_telepon) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, reseller.getNamaReseller());
            pstmt.setString(2, reseller.getProdukTitipan());
            pstmt.setString(3, reseller.getNoTelepon());
            
            pstmt.executeUpdate();
            System.out.println("Data reseller berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data: " + e.getMessage());
        }
    }

    // 2. READ ( show all reseller data )
    public List<Reseller> getAll() {
        List<Reseller> listReseller = new ArrayList<>();
        String query = "SELECT * FROM reseller";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Reseller reseller = new Reseller();
                reseller.setId(rs.getInt("id"));
                reseller.setNamaReseller(rs.getString("nama_reseller"));
                reseller.setProdukTitipan(rs.getString("produk_titipan"));
                reseller.setNoTelepon(rs.getString("no_telepon"));
                listReseller.add(reseller);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }
        return listReseller;
    }

    // 3. UPDATE ( editing reseller data )
    public void update(Reseller reseller) {
        String query = "UPDATE reseller SET nama_reseller = ?, produk_titipan = ?, no_telepon = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, reseller.getNamaReseller());
            pstmt.setString(2, reseller.getProdukTitipan());
            pstmt.setString(3, reseller.getNoTelepon());
            pstmt.setInt(4, reseller.getId());
            
            pstmt.executeUpdate();
            System.out.println("Data reseller berhasil diupdate!");
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate data: " + e.getMessage());
        }
    }

    // 4. DELETE ( deleting reseller data )
    public void delete(int id) {
        String query = "DELETE FROM reseller WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Data reseller berhasil dihapus!");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }
}