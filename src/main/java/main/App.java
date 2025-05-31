package main;

import ui.FormPendaftaran;
import ui.TampilData;
import service.DatabaseService;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        DatabaseService.initDatabase();
        SwingUtilities.invokeLater(() -> {
            FormPendaftaran form = new FormPendaftaran();
            form.setVisible(true);

            // Tambahkan tombol tampilkan data
            JButton tombolLihatData = new JButton("Tampilkan Data");
            tombolLihatData.addActionListener(e -> new TampilData().setVisible(true));

            // Tambahkan tombol ke form
            form.getContentPane().add(tombolLihatData, "South");
        });
    }
}
