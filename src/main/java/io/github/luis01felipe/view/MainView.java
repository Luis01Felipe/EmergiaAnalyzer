package io.github.luis01felipe.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import io.github.luis01felipe.controller.CalculadoraEmergiaController;

public class MainView {

    private JFrame window;
    private List<JLabel> labels;
    private JTextField energyField, waterField, treatmentField;
    private JLabel energyResultLabel, waterResultLabel, treatmentResultLabel, totalResultLabel;
    private CalculadoraEmergiaController controller;

    // O Controller vai se comunicar com a View para atualizar os resultados
    public MainView(CalculadoraEmergiaController controller) {
        this.controller = controller;
        initializeComponents();
        configureWindow();
        createLabels();
        createInputsAndButtons();
        configureLabels();
        assembleLayout();
        showWindow();
    }

    // Inicializa os componentes principais
    private void initializeComponents() {
        window = new JFrame("Emergia Analyzer");
        labels = new ArrayList<>();
    }

    // Configurações do JFrame
    private void configureWindow() {
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
    }

    // Cria as labels e as adiciona à lista
    private void createLabels() {
        JLabel titleLabel = new JLabel("Emergia Analyzer", SwingConstants.CENTER);
        JLabel footerLabel = new JLabel("Rodapé da aplicação", SwingConstants.CENTER);

        labels.add(titleLabel);
        labels.add(footerLabel);
    }

    // Cria os campos de entrada, botões e labels de resultados
    private void createInputsAndButtons() {
        // Campos de texto para entrada de dados
        energyField = new JTextField(10);
        waterField = new JTextField(10);
        treatmentField = new JTextField(10);

        // Botão para calcular a emergia
        JButton calculateButton = new JButton("Calcular Emergía");

        // Labels de resultados
        energyResultLabel = new JLabel("Emergia do Consumo de Energia: 0 sej");
        waterResultLabel = new JLabel("Emergia do Consumo de Água: 0 sej");
        treatmentResultLabel = new JLabel("Emergia do Tratamento de Água: 0 sej");
        totalResultLabel = new JLabel("Emergia Total Consumida: 0 sej");

        // Adiciona o botão e os campos de entrada em um painel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Consumo de Energia (kWh/mês):"));
        inputPanel.add(energyField);
        inputPanel.add(new JLabel("Consumo de Água (litros/dia):"));
        inputPanel.add(waterField);
        inputPanel.add(new JLabel("Tratamento de Água (m³/mês):"));
        inputPanel.add(treatmentField);
        inputPanel.add(calculateButton);

        // Adiciona as labels de resultados em um painel
        JPanel resultPanel = new JPanel(new GridLayout(4, 1));
        resultPanel.add(energyResultLabel);
        resultPanel.add(waterResultLabel);
        resultPanel.add(treatmentResultLabel);
        resultPanel.add(totalResultLabel);

        // Adiciona os painéis ao JFrame
        window.add(inputPanel, BorderLayout.CENTER);
        window.add(resultPanel, BorderLayout.SOUTH);

        // Configura o botão para chamar o Controller
        calculateButton.addActionListener(e -> onCalculateButtonClick());
    }

    // Configura todas as labels de uma vez
    private void configureLabels() {
        for (JLabel label : labels) {
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.setForeground(Color.BLACK);
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }
        labels.get(0).setFont(new Font("Arial", Font.BOLD, 24)); // Para o título
        labels.get(1).setFont(new Font("Arial", Font.ITALIC, 16)); // Para o rodapé
    }

    // Monta a interface com as labels
    private void assembleLayout() {
        window.add(labels.get(0), BorderLayout.NORTH); // Título
    }

    // Exibe a janela
    private void showWindow() {
        window.setVisible(true);
    }

    // Método que chama o Controller
    private void onCalculateButtonClick() {
        try {
            // Coleta os dados dos campos de entrada
            double energy = Double.parseDouble(energyField.getText());
            double water = Double.parseDouble(waterField.getText());
            double treatment = Double.parseDouble(treatmentField.getText());

            // Chama o método do controller para realizar os cálculos
            controller.onCalculateButtonClick(energy, water, treatment);
        } catch (NumberFormatException e) {
            // Exceção caso os campos estejam vazios ou com valores inválidos
            JOptionPane.showMessageDialog(window, "Por favor, insira valores válidos.");
        }
    }

    // Métodos para a View atualizar os resultados
    public void updateResults(double energyEmergia, double waterEmergia, double treatmentEmergia, double totalEmergia) {
        energyResultLabel.setText("Emergia do Consumo de Energia: " + energyEmergia + " sej");
        waterResultLabel.setText("Emergia do Consumo de Água: " + waterEmergia + " sej");
        treatmentResultLabel.setText("Emergia do Tratamento de Água: " + treatmentEmergia + " sej");
        totalResultLabel.setText("Emergia Total Consumida: " + totalEmergia + " sej");
    }
}