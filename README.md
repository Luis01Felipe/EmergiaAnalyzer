Notion: https://www.notion.so/Atividade-Pr-tica-Supervisionada-APS-1e5771b3360280edac66e119b7e1e305?pvs=4

O Programa deve seguir tudo o que está no arquivo 'Engnharia Software - Orientações - Aluno.pdf'
- [ ] Estudar MVC
- [ ] Estudar SOLID
- [ ] Estudar Clean Code

O objetivo é criar uma aplicação que realize cálculos emergéticos utilizando bases de dados de Inventários do Ciclo de Vida (LCI) e implemente as regras da álgebra emergética de forma rigorosa.

🔍 Organização por Camada
✅ Model (Regra de Negócio / Entidades)
ItemEmergia: representa uma linha do inventário (com dados como nome, valor emergético etc.).

Inventario: contém uma lista de ItemEmergia, faz a leitura inicial dos dados.

CalculadoraEmergia: faz o cálculo de emergia total a partir do Inventario.

ValidadorLCI: valida os dados lidos.

🔹 Princípios aplicados:

SRP (Single Responsibility) → cada classe com uma responsabilidade.

OCP (Open/Closed) → Calculadora pode ser estendida para novos cálculos sem ser modificada.

✅ Controller (Intermediações e lógica de controle)
ImportacaoController: coordena a leitura de arquivos CSV.

CalculadoraEmergiaController: aciona o cálculo e passa os dados para a View.

InventarioController: gerencia as interações com o inventário.

🔹 Princípios aplicados:

Separa bem lógica de controle da lógica de negócio.

Controladores recebem eventos da View e operam sobre Model.

✅ View (Interface com o usuário)
MainView: interface principal (pode ser CLI ou GUI, como um menu ou tela inicial).

ResultadoView: exibe os resultados do cálculo.

🔹 Princípios aplicados:

DIP (Dependency Inversion): View não manipula diretamente Model, mas sim via Controller.

✅ Utils (Funções auxiliares reutilizáveis)
LeitorCSV: separa lógica de leitura de arquivos (não deve estar no Model).

FormatadorTexto: (opcional) para padronizar saídas e mensagens.

✅ Classe Principal
App.java: inicia a aplicação, cria as instâncias de Controller e View, e inicia o fluxo.

