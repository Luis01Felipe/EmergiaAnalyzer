package io.github.luis01felipe.emergyanalyzer.controller;

import io.github.luis01felipe.emergyanalyzer.model.EmergyCalculatorModel;
import io.github.luis01felipe.emergyanalyzer.view.MainView;

public class EmergyCalculatorController {

    private final EmergyCalculatorModel model;
    private MainView view;

    public EmergyCalculatorController(EmergyCalculatorModel model) {
        this.model = model;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    // Método chamado quando o botão de calcular é pressionado
    public void onCalculateButtonClick(double energy, double water, double treatment) {
        double energyEmergia = model.calculateEnergy(energy);
        double waterEmergia = model.calculateWater(water);
        double treatmentEmergia = model.calculateTreatment(treatment);
        double totalEmergia = model.calcularTotalEmergia(energy, water, treatment);

        // Passa os resultados para a View
        if (view != null) {
            view.updateResults(energyEmergia, waterEmergia, treatmentEmergia, totalEmergia);
        }
    }
}
