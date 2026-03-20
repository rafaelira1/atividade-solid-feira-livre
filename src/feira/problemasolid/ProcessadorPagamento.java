package feira.solucao.pagamento;

public interface ProcessadorPagamento {
    String codigo();
    void pagar(double valor);
}
