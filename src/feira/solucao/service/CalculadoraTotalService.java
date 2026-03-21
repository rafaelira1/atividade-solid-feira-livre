package feira.solucao.service;

import feira.solucao.domain.Pedido;

public class CalculadoraTotalService {
    public double calcularTotal(Pedido pedido) {
        return pedido.totalBruto();
    }
}