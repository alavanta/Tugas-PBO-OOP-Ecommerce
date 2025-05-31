package model;

public class UserData {
    private int id;
    private String nama;
    private String email;
    private String role;
    private String kategori;

    public UserData(int id, String nama, String email, String role, String kategori) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.role = role;
        this.kategori = kategori;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getKategori() {
        return kategori;
    }
}
