package io.github.luis01felipe.emergyanalyzer;

import io.github.luis01felipe.emergyanalyzer.controller.EmergyCalculatorController;
import io.github.luis01felipe.emergyanalyzer.controller.LCIMatrixController;
import io.github.luis01felipe.emergyanalyzer.model.EmergyCalculatorModel;
import io.github.luis01felipe.emergyanalyzer.model.LCIMatrix;  // Importando LCIMatrix
import io.github.luis01felipe.emergyanalyzer.model.TransformityProvider;  // Importando TransformityProvider
import io.github.luis01felipe.emergyanalyzer.view.EmergyCalculatorView;

import java.io.File;

public class EmergyAnalyzer {
    public static void main(String[] args) {
        // Cria o modelo (EmergyCalculatorModel)
        EmergyCalculatorModel model = new EmergyCalculatorModel();

        // Cria as instâncias de LCIMatrix e TransformityProvider
        LCIMatrix lciMatrix = new LCIMatrix(); // Crie uma instância de LCIMatrix
        TransformityProvider transformityProvider = new TransformityProvider(); // Crie uma instância de TransformityProvider

        // Cria o LCIMatrixController passando os objetos necessários
        LCIMatrixController lciMatrixController = new LCIMatrixController(lciMatrix, transformityProvider);

        // Cria o controlador e passa o modelo e o LCIMatrixController
        EmergyCalculatorController controller = new EmergyCalculatorController(model, lciMatrixController);

        // Cria a view e passa o controlador
        EmergyCalculatorView view = new EmergyCalculatorView(controller);

        // Cria e associa a view ao controlador
        controller.setView(view);

        // Carrega um arquivo CSV de teste
        File csvFile = new File("test_data.csv"); // Substitua com o caminho do seu arquivo CSV
        controller.loadFromCSV(csvFile);
    }
}
