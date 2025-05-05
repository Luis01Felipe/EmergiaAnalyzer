package io.github.luis01felipe.model;

public class CalculadoraEmergia {

    // Função de conversão de kWh para sej
    public double kwhToSej(double kwh) {
        return kwh * 3.6e6;  // 1 kWh = 3.6e6 sej
    }

    // Função de conversão de litros para sej
    public double litroToSej(double litros) {
        return litros * 0.5;  // 1 litro de água = 0.5 sej
    }

    // Função de conversão de m³ para sej
    public double m3ToSej(double m3) {
        return m3 * 1.8e6;  // 1 m³ de água = 1.8e6 sej
    }

    // Função para calcular a emergia total
    public double calcularEnergiaEmergia(double consumoEnergia) {
        return kwhToSej(consumoEnergia);
    }

    public double calcularWaterEmergia(double consumoAgua) {
        return litroToSej(consumoAgua * 30);  // 30 dias por mês
    }

    public double calcularTreatmentEmergia(double tratamentoAgua) {
        return m3ToSej(tratamentoAgua);
    }

    // Função para calcular a emergia total
    public double calcularTotalEmergia(double energia, double agua, double tratamento) {
        double emergiaEnergia = calcularEnergiaEmergia(energia);
        double emergiaAgua = calcularWaterEmergia(agua);
        double emergiaTratamento = calcularTreatmentEmergia(tratamento);
        return emergiaEnergia + emergiaAgua + emergiaTratamento;
    }
}
