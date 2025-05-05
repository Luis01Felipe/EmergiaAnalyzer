package io.github.luis01felipe.emergyanalyzer;

import io.github.luis01felipe.emergyanalyzer.controller.EmergyCalculatorController;
import io.github.luis01felipe.emergyanalyzer.model.EmergyCalculatorModel;
import io.github.luis01felipe.emergyanalyzer.view.MainView;

public class EmergyAnalyzer {
    public static void main(String[] args) {
        // Cria o modelo (EmergyCalculatorModel)
        EmergyCalculatorModel model = new EmergyCalculatorModel();

        // Cria o controlador e passa o modelo
        EmergyCalculatorController controller = new EmergyCalculatorController(model);

        // Cria a view e passa o controlador
        MainView view = new MainView(controller);  // Agora passando o controlador

        // Cria e associa a view ao controlador
        controller.setView(view);  // Defina esse método no controlador, se necessário
    }
}
