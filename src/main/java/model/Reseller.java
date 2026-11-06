/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class Reseller {
    private int id;
    private String namaReseller;
    private String produkTitipan;
    private String noTelepon;

    // constructor
    public Reseller() {}

    // constructor with parameter
    public Reseller(int id, String namaReseller, String produkTitipan, String noTelepon) {
        this.id = id;
        this.namaReseller = namaReseller;
        this.produkTitipan = produkTitipan;
        this.noTelepon = noTelepon;
    }

    // getter & setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNamaReseller() { return namaReseller; }
    public void setNamaReseller(String namaReseller) { this.namaReseller = namaReseller; }

    public String getProdukTitipan() { return produkTitipan; }
    public void setProdukTitipan(String produkTitipan) { this.produkTitipan = produkTitipan; }

    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }
}