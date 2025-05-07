package io.github.luis01felipe.emergyanalyzer.model;

import java.util.HashMap;
import java.util.Map;

public class TransformityProvider {

    private final Map<String, Double> transformities;

    public TransformityProvider() {
        transformities = new HashMap<>();

        // Adicione aqui os valores conhecidos
        transformities.put("Electricity", 1.2e5);
        transformities.put("Water", 3.4e4);
        transformities.put("Fuel", 2.6e5);
        // Você pode expandir com mais tipos de recurso
    }

    public double getTransformity(String resource) {
        return transformities.getOrDefault(resource, 0.0);
    }
}