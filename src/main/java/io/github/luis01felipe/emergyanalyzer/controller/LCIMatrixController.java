package io.github.luis01felipe.emergyanalyzer.controller;

import io.github.luis01felipe.emergyanalyzer.model.LCIMatrix;
import io.github.luis01felipe.emergyanalyzer.model.TransformityProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LCIMatrixController {
    private static final Logger logger = LoggerFactory.getLogger(LCIMatrixController.class);
    private final LCIMatrix lciMatrix;
    private final TransformityProvider transformityProvider;

    // Lista para armazenar os dados do CSV para visualização
    private List<String[]> csvData = new ArrayList<>();

    public LCIMatrixController(LCIMatrix lciMatrix, TransformityProvider transformityProvider) {
        this.lciMatrix = lciMatrix;
        this.transformityProvider = transformityProvider;
    }

    public List<String[]> loadFromCSV(File csvFile) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            String[] headers = br.readLine().split(",");

            data.add(headers); // Adiciona cabeçalho

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values); // Adiciona linha na tabela
                populateLCIMatrix(headers, values); // Popula a lciMatrix
            }

            csvData = data; // Armazena os dados lidos para visualização

        } catch (IOException | NumberFormatException e) {
            logger.error("Erro ao carregar dados do CSV: {}", e.getMessage(), e);
        }

        return data;
    }

    private void populateLCIMatrix(String[] headers, String[] values) {
        String resource = values[0];
        for (int i = 1; i < values.length; i++) {
            String process = headers[i];
            try {
                double quantity = Double.parseDouble(values[i]);
                lciMatrix.addEntry(resource, process, quantity);
            } catch (NumberFormatException e) {
                logger.warn("Valor inválido para o recurso {} e processo {}: {}", resource, process, values[i]);
            }
        }
    }

    // Método para obter os dados carregados para visualização
    public List<String[]> getCsvData() {
        return new ArrayList<>(csvData); // Retorna uma cópia dos dados
    }

    public double calculateEmergy(String process) {
        double totalEmergy = 0.0;

        for (String resource : lciMatrix.getMatrix().keySet()) {
            double quantity = lciMatrix.getValue(resource, process);
            double transformity = transformityProvider.getTransformity(resource);
            totalEmergy += quantity * transformity;
        }

        return totalEmergy;
    }
}
