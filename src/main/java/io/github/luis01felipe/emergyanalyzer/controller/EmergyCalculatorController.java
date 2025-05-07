package io.github.luis01felipe.emergyanalyzer.controller;

import io.github.luis01felipe.emergyanalyzer.model.EmergyCalculatorModel;
import io.github.luis01felipe.emergyanalyzer.view.EmergyCalculatorView;
import io.github.luis01felipe.emergyanalyzer.controller.LCIMatrixController; // Importando o LCIMatrixController

import java.io.File;
import java.util.List;

public class EmergyCalculatorController {

    private final EmergyCalculatorModel model;
    private EmergyCalculatorView view;
    private final LCIMatrixController lciMatrixController; // Usando LCIMatrixController

    // Corrigido: agora recebemos o LCIMatrixController no construtor
    public EmergyCalculatorController(EmergyCalculatorModel model, LCIMatrixController lciMatrixController) {
        this.model = model;
        this.lciMatrixController = lciMatrixController; // Inicializando corretamente o LCIMatrixController
    }

    public void setView(EmergyCalculatorView view) {
        this.view = view;
    }

    // Método para carregar o CSV e armazenar os dados usando LCIMatrixController
    public List<String[]> loadFromCSV(File csvFile) {
        return lciMatrixController.loadFromCSV(csvFile);  // Chama o método de LCIMatrixController
    }

    // Método para obter os dados carregados do CSV
    public List<String[]> getCsvData() {
        return lciMatrixController.getCsvData();  // Chama o método de LCIMatrixController
    }

    // Método chamado quando o botão de calcular é pressionado
    public void onCalculateButtonClick(double energy, double water, double treatment) {
        double energyEmergy = model.calculateEnergy(energy);
        double waterEmergy = model.calculateWater(water);
        double treatmentEmergy = model.calculateTreatment(treatment);
        double totalEmergy = model.calcularTotalEmergia(energy, water, treatment);

        // Passa os resultados para a View
        if (view != null) {
            view.updateResults(energyEmergy, waterEmergy, treatmentEmergy, totalEmergy);
        }
    }
}