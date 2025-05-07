package io.github.luis01felipe.emergyanalyzer.model;

import java.util.HashMap;
import java.util.Map;

public class LCIMatrix {
    private final Map<String, Map<String, Double>> matrix;

    public LCIMatrix() {
        this.matrix = new HashMap<>();
    }

    public void addEntry(String resource, String process, double value) {
        matrix.computeIfAbsent(resource, k -> new HashMap<>()).put(process, value);
    }

    public double getValue(String resource, String process) {
        return matrix.getOrDefault(resource, new HashMap<>()).getOrDefault(process, 0.0);
    }

    public Map<String, Map<String, Double>> getMatrix() {
        return matrix;
    }
}
