# Atividade SOLID — Feira Livre

## Integrantes

- Rafael Lira (rafaelira1)
- Gabriel Alves

## Turma

T200-88

---

## Descrição das melhorias realizadas

O código original (`feira.problemasolid`) concentrava toda a lógica do sistema na classe `ProcessadorPedidoRuim`, violando os cinco princípios SOLID. A refatoração foi realizada no pacote `feira.solucao`, organizada por etapas:

### SRP — Single Responsibility Principle
A classe `ProcessadorPedidoRuim` foi decomposta em classes com responsabilidades únicas:
- `FinalizadorPedidoService`: apenas orquestra o fluxo de finalização.
- `PedidoRepositoryMemoria`: cuida exclusivamente da persistência.
- `ImpressoraTermica`: responsável pela impressão do cupom.
- `NotificadorWhatsApp`: responsável pelo envio de notificações.
- `ExportadorCsvPedido`: responsável pela geração do relatório CSV.

### OCP — Open/Closed Principle
Descontos e pagamentos deixaram de usar `if/else` centralizados. Agora seguem o padrão Strategy:
- `PoliticaDesconto` é uma interface; novos descontos são adicionados sem alterar o orquestrador.
- `ProcessadorPagamento` é uma interface; novos meios de pagamento são adicionados sem alterar o orquestrador.

### ISP — Interface Segregation Principle
A interface de pagamento original era larga e forçava métodos desnecessários. A nova interface `ProcessadorPagamento` é mínima, contendo apenas `codigo()` e `pagar(valor)`, e cada implementação usa somente o que precisa.

### DIP — Dependency Inversion Principle
`FinalizadorPedidoService` passou a depender exclusivamente de abstrações (interfaces), recebendo todas as dependências via construtor. A criação das implementações concretas foi centralizada em `SolucaoMain`.

### LSP — Liskov Substitution Principle
A hierarquia de entrega foi reescrita com a interface `CalculadoraPrazoEntrega`. Tanto `EntregaNormal` quanto `EntregaExpressa` implementam o mesmo contrato e são usadas de forma totalmente intercambiável, sem tratamentos especiais.

---

## Como compilar e executar

### Pré-requisito
- Java 8 ou superior instalado.

### No PowerShell (Windows), dentro da pasta do projeto:

```powershell
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out feira.solucao.SolucaoMain
```

### No terminal (Linux/macOS):

```bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out feira.solucao.SolucaoMain
```

---

## Estrutura do projeto

```
src/
  feira/
    problemasolid/         ← código original (não alterado)
      AtividadeMain.java
      Entrega.java
      EntregaExpressa.java
      ImpressoraTermica.java
      NotificadorWhatsApp.java
      PagamentoGateway.java
      PagamentoPix.java
      Pedido.java
      PedidoItem.java
      PedidoRepository.java
      PedidoRepositoryMemoria.java
      ProcessadorPedidoRuim.java
      Produto.java
    solucao/               ← refatoração aplicando SOLID
      domain/              ← Produto, PedidoItem, Pedido
      repository/          ← PedidoRepository, PedidoRepositoryMemoria
      desconto/            ← PoliticaDesconto e implementações (OCP)
      pagamento/           ← ProcessadorPagamento e implementações (OCP + ISP)
      cupom/               ← ImpressoraCupom, ImpressoraTermica (SRP)
      notificacao/         ← NotificadorPedido, NotificadorWhatsApp (SRP)
      relatorio/           ← ExportadorRelatorioPedido, ExportadorCsvPedido (SRP)
      entrega/             ← CalculadoraPrazoEntrega, EntregaNormal, EntregaExpressa (LSP)
      service/             ← FinalizadorPedidoService, PedidoFinalizado (DIP)
      SolucaoMain.java     ← ponto de entrada da solução
README.md
```