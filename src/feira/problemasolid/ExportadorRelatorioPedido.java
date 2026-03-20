package feira.solucao.relatorio;

import feira.solucao.domain.Pedido;

public interface ExportadorRelatorioPedido {
    String exportar(Pedido pedido, double totalLiquido);
}
