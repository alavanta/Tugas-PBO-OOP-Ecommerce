package model;

import interfaceku.DaftarAkun;

public class Admin extends User implements DaftarAkun {
    public Admin(String nama, String email) {
        super(nama, email);
    }

    @Override
    public void daftar() {
        System.out.println("Admin berhasil didaftarkan.");
    }
}
