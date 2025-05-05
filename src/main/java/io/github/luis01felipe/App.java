package io.github.luis01felipe;

import io.github.luis01felipe.controller.CalculadoraEmergiaController;
import io.github.luis01felipe.model.CalculadoraEmergia;
import io.github.luis01felipe.view.MainView;

public class App {
    public static void main(String[] args) {
        // Cria o modelo (CalculadoraEmergia)
        CalculadoraEmergia model = new CalculadoraEmergia();

        // Cria o controlador e passa o modelo
        CalculadoraEmergiaController controller = new CalculadoraEmergiaController(model);

        // Cria a view e passa o controlador
        MainView view = new MainView(controller);  // Agora passando o controlador

        // Cria e associa a view ao controlador
        controller.setView(view);  // Defina esse método no controlador, se necessário
    }
}