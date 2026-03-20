package feira.solucao.desconto;

public interface PoliticaDesconto {
    String codigo();
    double aplicar(double totalBruto);
}
