package io.github.luis01felipe.emergyanalyzer.controller;

import io.github.luis01felipe.emergyanalyzer.model.EmergyCalculatorModel;
import io.github.luis01felipe.emergyanalyzer.view.EmergyCalculatorView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class EmergyCalculatorControllerTest {

    private EmergyCalculatorModel mockModel;
    private EmergyCalculatorView mockView;
    private LCIMatrixController mockLCIMatrixController;
    private EmergyCalculatorController controller;

    @BeforeEach
    void setUp() {
        mockModel = mock(EmergyCalculatorModel.class);
        mockView = mock(EmergyCalculatorView.class);
        mockLCIMatrixController = mock(LCIMatrixController.class);
        controller = new EmergyCalculatorController(mockModel, mockLCIMatrixController);
        controller.setView(mockView);
    }

    @Test
    void testOnCalculateButtonClick() {
        // Dados de entrada
        double energy = 10.0;
        double water = 5.0;
        double treatment = 2.0;

        // Comportamento esperado do model
        when(mockModel.calculateEnergy(energy)).thenReturn(100.0);
        when(mockModel.calculateWater(water)).thenReturn(50.0);
        when(mockModel.calculateTreatment(treatment)).thenReturn(20.0);
        when(mockModel.calcularTotalEmergia(energy, water, treatment)).thenReturn(170.0);

        controller.onCalculateButtonClick(energy, water, treatment);

        // Verifica se o método da view foi chamado com os valores esperados
        verify(mockView).updateResults(100.0, 50.0, 20.0, 170.0);
    }

    @Test
    void testLoadFromCSVDelegatesToLCIMatrixController() {
        File fakeFile = mock(File.class);
        List<String[]> fakeData = Arrays.asList(new String[]{"A", "B"}, new String[]{"1", "2"});

        when(mockLCIMatrixController.loadFromCSV(fakeFile)).thenReturn(fakeData);

        List<String[]> result = controller.loadFromCSV(fakeFile);

        verify(mockLCIMatrixController).loadFromCSV(fakeFile);
        assert result.equals(fakeData);
    }

    @Test
    void testGetCsvDataDelegatesToLCIMatrixController() {
        List<String[]> fakeData = Arrays.asList(new String[]{"X", "Y"}, new String[]{"3", "4"});

        when(mockLCIMatrixController.getCsvData()).thenReturn(fakeData);

        List<String[]> result = controller.getCsvData();

        verify(mockLCIMatrixController).getCsvData();
        assert result.equals(fakeData);
    }
}
