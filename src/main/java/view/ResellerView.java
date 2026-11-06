/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author ACER
 */
import model.Reseller;
import service.ResellerDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ResellerView extends JFrame {
    private JTextField txtId, txtNama, txtProduk, txtTelepon;
    private JButton btnTambah, btnEdit, btnHapus, btnClear;
    private JTable tableReseller;
    private DefaultTableModel tableModel;
    private ResellerDAO resellerDAO;

    public ResellerView() {
        resellerDAO = new ResellerDAO();

        // setting frame utama
        setTitle("LabaKu - Kelola Data Reseller Bento");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- 1. FORM INPUT ---
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 8, 8));
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Data Reseller"));

        panelForm.add(new JLabel("ID Reseller (Auto/Pilih Tabel):"));
        txtId = new JTextField();
        txtId.setEditable(false); // ID cannot be typed manually
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nama Reseller:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Produk Titipan:"));
        txtProduk = new JTextField();
        panelForm.add(txtProduk);

        panelForm.add(new JLabel("No. Telepon:"));
        txtTelepon = new JTextField();
        panelForm.add(txtTelepon);

        // --- 2. TOMBOL UTAMA ---
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear Form");

        panelTombol.add(btnTambah);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        add(panelAtas, BorderLayout.NORTH);

        // --- 3. TABEL DATA ---
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama Reseller", "Produk Titipan", "No. Telepon"}, 0);
        tableReseller = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableReseller);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Reseller Aktif"));

        add(scrollPane, BorderLayout.CENTER);

        // --- EVENT HANDLERS ---
        
        // add data
        btnTambah.addActionListener(e -> {
            if (txtNama.getText().isEmpty() || txtProduk.getText().isEmpty() || txtTelepon.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Reseller r = new Reseller(0, txtNama.getText(), txtProduk.getText(), txtTelepon.getText());
            resellerDAO.insert(r);
            loadDataTabel();
            clearForm();
        });

        // edit data
        btnEdit.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih data dari tabel yang ingin di-edit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = Integer.parseInt(txtId.getText());
            Reseller r = new Reseller(id, txtNama.getText(), txtProduk.getText(), txtTelepon.getText());
            resellerDAO.update(r);
            loadDataTabel();
            clearForm();
        });

        // delete data
        btnHapus.addActionListener(e -> {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih data dari tabel yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(txtId.getText());
                resellerDAO.delete(id);
                loadDataTabel();
                clearForm();
            }
        });

        // clear form
        btnClear.addActionListener(e -> clearForm());

        // klik tabel
        tableReseller.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableReseller.getSelectedRow();
                if (row != -1) {
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtNama.setText(tableModel.getValueAt(row, 1).toString());
                    txtProduk.setText(tableModel.getValueAt(row, 2).toString());
                    txtTelepon.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        // load data
        loadDataTabel();
    }

    // load data tabel
    private void loadDataTabel() {
        tableModel.setRowCount(0); // clear tabel
        List<Reseller> list = resellerDAO.getAll();
        for (Reseller r : list) {
            tableModel.addRow(new Object[]{r.getId(), r.getNamaReseller(), r.getProdukTitipan(), r.getNoTelepon()});
        }
    }

    // clear form
    private void clearForm() {
        txtId.setText("");
        txtNama.setText("");
        txtProduk.setText("");
        txtTelepon.setText("");
        tableReseller.clearSelection();
    }
}