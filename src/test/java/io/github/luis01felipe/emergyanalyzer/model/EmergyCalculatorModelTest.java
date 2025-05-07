package io.github.luis01felipe.emergyanalyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmergyCalculatorModelTest {

    private EmergyCalculatorModel model;

    @BeforeEach
    void setUp() {
        model = new EmergyCalculatorModel();
    }

    @Test
    void testKwhToSej() {
        double kwh = 5.0;  // Exemplo de consumo de 5 kWh
        double expectedSej = 5.0 * 3.6e6;  // 1 kWh = 3.6e6 sej

        double result = model.kwhToSej(kwh);

        assertEquals(expectedSej, result, "A conversão de kWh para sej está incorreta.");
    }

    @Test
    void testLiterToSej() {
        double liters = 10.0;  // Exemplo de 10 litros
        double expectedSej = 10.0 * 0.5;  // 1 litro de água = 0.5 sej

        double result = model.literToSej(liters);

        assertEquals(expectedSej, result, "A conversão de litros para sej está incorreta.");
    }

    @Test
    void testM3ToSej() {
        double m3 = 3.0;  // Exemplo de 3 m³
        double expectedSej = 3.0 * 1.8e6;  // 1 m³ de água = 1.8e6 sej

        double result = model.m3ToSej(m3);

        assertEquals(expectedSej, result, "A conversão de m³ para sej está incorreta.");
    }

    @Test
    void testCalculateEnergy() {
        double energyConsumption = 5.0;  // Exemplo de consumo de 5 kWh
        double expectedEmergy = 5.0 * 3.6e6;  // 1 kWh = 3.6e6 sej

        double result = model.calculateEnergy(energyConsumption);

        assertEquals(expectedEmergy, result, "O cálculo da emergia de energia está incorreto.");
    }

    @Test
    void testCalculateWater() {
        double waterUsage = 10.0;  // Exemplo de consumo de 10 litros por dia
        double expectedEmergy = 10.0 * 30 * 0.5;  // 30 dias por mês, 1 litro = 0.5 sej

        double result = model.calculateWater(waterUsage);

        assertEquals(expectedEmergy, result, "O cálculo da emergia de água está incorreto.");
    }

    @Test
    void testCalculateTreatment() {
        double waterTreatment = 3.0;  // Exemplo de 3 m³ de água tratada
        double expectedEmergy = 3.0 * 1.8e6;  // 1 m³ de água = 1.8e6 sej

        double result = model.calculateTreatment(waterTreatment);

        assertEquals(expectedEmergy, result, "O cálculo da emergia de tratamento está incorreto.");
    }

    @Test
    void testCalcularTotalEmergia() {
        double energy = 5.0;  // 5 kWh
        double water = 10.0;  // 10 litros por dia
        double treatment = 3.0;  // 3 m³ de tratamento

        // Cálculo manual esperado
        double expectedEmergy = (5.0 * 3.6e6) + (10.0 * 30 * 0.5) + (3.0 * 1.8e6);

        double result = model.calcularTotalEmergia(energy, water, treatment);

        assertEquals(expectedEmergy, result, "O cálculo da emergia total está incorreto.");
    }
}
