package feira.solucao.cupom;

import feira.solucao.domain.Pedido;

public interface ImpressoraCupom {
    void imprimir(Pedido pedido, double totalLiquido);
}
