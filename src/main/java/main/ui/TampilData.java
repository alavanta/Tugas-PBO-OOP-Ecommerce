package ui;

import model.UserData;
import service.DatabaseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TampilData extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public TampilData() {
        setTitle("Data User E-Commerce");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columns = {"ID", "Nama", "Email", "Role", "Kategori"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        List<UserData> users = DatabaseService.getAllUsers();
        for (UserData user : users) {
            Object[] row = {
                    user.getId(),
                    user.getNama(),
                    user.getEmail(),
                    user.getRole(),
                    user.getKategori()
            };
            tableModel.addRow(row);
        }
    }
}
