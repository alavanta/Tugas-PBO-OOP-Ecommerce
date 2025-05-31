package ui;

import model.Admin;
import model.Customer;
import model.User;
import service.DatabaseService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPendaftaran extends JFrame {
    private JTextField fieldNama = new JTextField(20);
    private JTextField fieldEmail = new JTextField(20);
    private JRadioButton rbCustomer = new JRadioButton("Customer");
    private JRadioButton rbAdmin = new JRadioButton("Admin");
    private JComboBox<String> cbKategori = new JComboBox<>(new String[]{"Fashion", "Elektronik", "Makanan"});
    private JButton btnDaftar = new JButton("Daftar");

    public FormPendaftaran() {
        setTitle("Form Pendaftaran E-Commerce");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ButtonGroup group = new ButtonGroup();
        group.add(rbCustomer);
        group.add(rbAdmin);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nama:"));
        panel.add(fieldNama);
        panel.add(new JLabel("Email:"));
        panel.add(fieldEmail);
        panel.add(new JLabel("Tipe Akun:"));
        JPanel radioPanel = new JPanel();
        radioPanel.add(rbCustomer);
        radioPanel.add(rbAdmin);
        panel.add(radioPanel);
        panel.add(new JLabel("Kategori Minat:"));
        panel.add(cbKategori);
        panel.add(new JLabel(""));
        panel.add(btnDaftar);

        add(panel);

        btnDaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = fieldNama.getText();
                String email = fieldEmail.getText();
                String kategori = cbKategori.getSelectedItem().toString();
                String role = rbCustomer.isSelected() ? "Customer" : rbAdmin.isSelected() ? "Admin" : "";

                if (nama.isEmpty() || email.isEmpty() || role.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Mohon lengkapi semua data!");
                    return;
                }

                User user;
                if (role.equals("Customer")) {
                    user = new Customer(nama, email);
                } else {
                    user = new Admin(nama, email);
                }

                try {
                    DatabaseService.simpanUser(nama, email, role, kategori);
                    JOptionPane.showMessageDialog(null, role + " berhasil didaftarkan!");
                    fieldNama.setText("");
                    fieldEmail.setText("");
                    cbKategori.setSelectedIndex(0);
                    group.clearSelection();
                } catch (Exception ex) {
                    ex.printStackTrace();  // tampilkan ke console
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}
