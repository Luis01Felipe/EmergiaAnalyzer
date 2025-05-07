package io.github.luis01felipe.emergyanalyzer.view;

import io.github.luis01felipe.emergyanalyzer.controller.EmergyCalculatorController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class EmergyCalculatorView {

    private final JFrame window;
    private final EmergyCalculatorController controller;
    public JTable csvTable;
    private DefaultTableModel tableModel;

    private JTextField energyField, waterField, treatmentField;
    private JLabel energyResultLabel, waterResultLabel, treatmentResultLabel, totalResultLabel;

    public EmergyCalculatorView(EmergyCalculatorController controller) {
        this.controller = controller;
        this.window = new JFrame("Emergy Analyzer");
        initializeUI();
    }

    private void initializeUI() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        window.add(createTitlePanel(), BorderLayout.NORTH);
        window.add(createCenterPanel(), BorderLayout.CENTER);

        window.setVisible(true);
    }

    private JPanel createTitlePanel() {
        JLabel title = new JLabel("Emergy Analyzer", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(34, 45, 65));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 240, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(title, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(createInputPanel(), BorderLayout.WEST);
        panel.add(createResultPanel(), BorderLayout.EAST);
        panel.add(createCsvTablePanel(), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Inputs"));

        energyField = new JTextField();
        waterField = new JTextField();
        treatmentField = new JTextField();

        panel.add(new JLabel("Energy Consumption (kWh/month):"));
        panel.add(energyField);
        panel.add(new JLabel("Water Consumption (liters/day):"));
        panel.add(waterField);
        panel.add(new JLabel("Water Treatment (m³/month):"));
        panel.add(treatmentField);

        JButton calculateButton = new JButton("Calculate Emergy");
        calculateButton.addActionListener(e -> onCalculateButtonClick());
        panel.add(calculateButton);

        JButton importButton = new JButton("Import CSV");
        importButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(window);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                List<String[]> csvData = controller.loadFromCSV(selectedFile);  // ← Agora retorna
                updateCsvData(csvData);  // ← Atualiza a tabela
            }
        });

        panel.add(importButton);

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Results"));

        energyResultLabel = new JLabel("Energy Emergy: 0 sej");
        waterResultLabel = new JLabel("Water Emergy: 0 sej");
        treatmentResultLabel = new JLabel("Treatment Emergy: 0 sej");
        totalResultLabel = new JLabel("Total Emergy: 0 sej");

        panel.add(energyResultLabel);
        panel.add(waterResultLabel);
        panel.add(treatmentResultLabel);
        panel.add(totalResultLabel);

        return panel;
    }

    private JPanel createCsvTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        csvTable = new JTable();
        tableModel = new DefaultTableModel();
        csvTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(csvTable);
        panel.setBorder(BorderFactory.createTitledBorder("CSV Data"));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void onCalculateButtonClick() {
        try {
            double energy = parseOrZero(energyField);
            double water = parseOrZero(waterField);
            double treatment = parseOrZero(treatmentField);

            controller.onCalculateButtonClick(energy, water, treatment);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(window, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onImportButtonClick() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a CSV file");
        int result = fileChooser.showOpenDialog(window);

        if (result == JFileChooser.APPROVE_OPTION) {
            File csvFile = fileChooser.getSelectedFile();
            controller.loadFromCSV(csvFile);
        }
    }

    public void updateCsvData(List<String[]> data) {
        if (data == null || data.isEmpty()) {
            JOptionPane.showMessageDialog(window, "CSV está vazio ou inválido.", "Erro ao importar CSV", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // DEBUG: ver os dados lidos
        System.out.println("DADOS CSV:");
        for (String[] linha : data) {
            System.out.println(Arrays.toString(linha));
        }

        String[] columns = data.get(0);
        tableModel.setColumnIdentifiers(columns);
        tableModel.setRowCount(0); // limpa dados anteriores

        for (int i = 1; i < data.size(); i++) {
            tableModel.addRow(data.get(i));
        }
    }

    public void updateResults(double energyEmergy, double waterEmergy, double treatmentEmergy, double totalEmergy) {
        energyResultLabel.setText("Energy Emergy: " + energyEmergy + " sej");
        waterResultLabel.setText("Water Emergy: " + waterEmergy + " sej");
        treatmentResultLabel.setText("Treatment Emergy: " + treatmentEmergy + " sej");
        totalResultLabel.setText("Total Emergy: " + totalEmergy + " sej");
    }

    private double parseOrZero(JTextField field) {
        String text = field.getText().trim();
        if (text.isEmpty()) return 0.0;
        return Double.parseDouble(text);
    }
}
