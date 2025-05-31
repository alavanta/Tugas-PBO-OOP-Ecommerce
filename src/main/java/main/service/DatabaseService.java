package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.UserData;



public class DatabaseService {
    private static final String URL = "jdbc:sqlite:ecommerce.db";

    // Static block untuk membuat tabel jika belum ada
    static {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            String createTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT NOT NULL, " +
                    "role TEXT NOT NULL, " +
                    "kategori TEXT" +
                    ");";

            stmt.execute(createTable);
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel: " + e.getMessage());
        }
    }

    // Method simpanUser
    public static void simpanUser(String nama, String email, String role, String kategori) {
        String sql = "INSERT INTO users(name, email, role, kategori) VALUES(?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nama);
            pstmt.setString(2, email);
            pstmt.setString(3, role);
            pstmt.setString(4, kategori);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    // Method ambil semua data users
    public static List<UserData> getAllUsers() {
        List<UserData> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                UserData user = new UserData(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("kategori")
                );
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }
        return list;
    }

    public static void initDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver SQLite tidak ditemukan: " + e.getMessage());
            return;
        }
        String createTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "role TEXT NOT NULL, " +
                "kategori TEXT" +
                ");";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTable);
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel: " + e.getMessage());
        }
    }

}
