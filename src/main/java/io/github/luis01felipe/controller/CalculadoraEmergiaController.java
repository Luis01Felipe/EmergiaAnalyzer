package io.github.luis01felipe.controller;

import io.github.luis01felipe.model.CalculadoraEmergia;
import io.github.luis01felipe.view.MainView;

public class CalculadoraEmergiaController {

    private CalculadoraEmergia model;
    private MainView view;

    public CalculadoraEmergiaController(CalculadoraEmergia model) {
        this.model = model;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    // Método chamado quando o botão de calcular é pressionado
    public void onCalculateButtonClick(double energy, double water, double treatment) {
        double energyEmergia = model.calcularEnergiaEmergia(energy);
        double waterEmergia = model.calcularWaterEmergia(water);
        double treatmentEmergia = model.calcularTreatmentEmergia(treatment);
        double totalEmergia = model.calcularTotalEmergia(energy, water, treatment);

        // Passa os resultados para a View
        if (view != null) {
            view.updateResults(energyEmergia, waterEmergia, treatmentEmergia, totalEmergia);
        }
    }
}
