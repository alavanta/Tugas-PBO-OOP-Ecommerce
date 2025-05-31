package model;

import interfaceku.DaftarAkun;

public class Customer extends User implements DaftarAkun {
    public Customer(String nama, String email) {
        super(nama, email);
    }

    @Override
    public void daftar() {
        System.out.println("Customer berhasil didaftarkan.");
    }
}
