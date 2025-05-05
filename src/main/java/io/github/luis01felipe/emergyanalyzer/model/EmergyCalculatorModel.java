package io.github.luis01felipe.emergyanalyzer.model;

public class EmergyCalculatorModel {

    // Função de conversão de kWh para sej
    public double kwhToSej(double kwh) {
        return kwh * 3.6e6;  // 1 kWh = 3.6e6 sej
    }

    // Função de conversão de litros para sej
    public double literToSej(double litros) {
        return litros * 0.5;  // 1 litro de água = 0.5 sej
    }

    // Função de conversão de m³ para sej
    public double m3ToSej(double m3) {
        return m3 * 1.8e6;  // 1 m³ de água = 1.8e6 sej
    }

    // Função para calcular a emergia total
    public double calculateEnergy(double energyConsumption) {
        return kwhToSej(energyConsumption);
    }

    public double calculateWater(double waterUsage) {
        return literToSej(waterUsage * 30);  // 30 dias por mês
    }

    public double calculateTreatment(double waterTreatment) {
        return m3ToSej(waterTreatment);
    }

    // Função para calcular a emergia total
    public double calcularTotalEmergia(double energia, double agua, double tratamento) {
        double emergiaEnergia = calculateEnergy(energia);
        double emergiaAgua = calculateWater(agua);
        double emergiaTratamento = calculateTreatment(tratamento);
        return emergiaEnergia + emergiaAgua + emergiaTratamento;
    }
}
