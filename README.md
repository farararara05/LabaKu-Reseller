# LabaKu - Reseller
Aplikasi desktop berbasis Java Swing dengan arsitektur MVC (Model-View-Controller) untuk mengelola data reseller dan produk titipan.

## Fitur Utama
- **Create**: Menambahkan data reseller baru beserta produk titipan.
- **Read**: Menampilkan daftar reseller dalam bentuk tabel (`JTable`).
- **Update**: Memperbarui data reseller yang sudah ada.
- **Delete**: Menghapus data reseller dari sistem.
- **Database Connection**: Terhubung secara langsung dengan database MySQL.

## Struktur Project (MVC)
- `model`: `Reseller.java` (Representasi data reseller)
- `service`: `DatabaseConnection.java` & `ResellerDAO.java` (Logika koneksi & query database)
- `view`: `ResellerView.java` (Antarmuka GUI Java Swing)
- `main`: `Main.java` (Class utama untuk menjalankan aplikasi)

## Requirements
- Java Development Kit (JDK) 
- MySQL Database Engine
- MySQL Connector / J (JDBC Driver)
