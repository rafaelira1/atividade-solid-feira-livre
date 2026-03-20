package feira.solucao.service;

import feira.solucao.cupom.ImpressoraCupom;
import feira.solucao.desconto.CalculadoraDesconto;
import feira.solucao.domain.Pedido;
import feira.solucao.notificacao.NotificadorPedido;
import feira.solucao.pagamento.ServicoPagamento;
import feira.solucao.relatorio.ExportadorRelatorioPedido;
import feira.solucao.repository.PedidoRepository;

public class FinalizadorPedidoService {
    private final PedidoRepository pedidoRepository;
    private final CalculadoraDesconto calculadoraDesconto;
    private final ServicoPagamento servicoPagamento;
    private final ImpressoraCupom impressoraCupom;
    private final NotificadorPedido notificadorPedido;
    private final ExportadorRelatorioPedido exportadorRelatorio;

    public FinalizadorPedidoService(
            PedidoRepository pedidoRepository,
            CalculadoraDesconto calculadoraDesconto,
            ServicoPagamento servicoPagamento,
            ImpressoraCupom impressoraCupom,
            NotificadorPedido notificadorPedido,
            ExportadorRelatorioPedido exportadorRelatorio) {
        this.pedidoRepository = pedidoRepository;
        this.calculadoraDesconto = calculadoraDesconto;
        this.servicoPagamento = servicoPagamento;
        this.impressoraCupom = impressoraCupom;
        this.notificadorPedido = notificadorPedido;
        this.exportadorRelatorio = exportadorRelatorio;
    }

    public PedidoFinalizado finalizar(Pedido pedido, String desconto, String pagamento, String contato) {
        if (pedido == null || pedido.vazio()) {
            throw new IllegalArgumentException("Pedido vazio ou nulo");
        }

        double totalBruto = pedido.totalBruto();
        double totalLiquido = calculadoraDesconto.aplicar(desconto, totalBruto);

        servicoPagamento.pagar(pagamento, totalLiquido);
        pedidoRepository.salvar(pedido);
        impressoraCupom.imprimir(pedido, totalLiquido);
        notificadorPedido.notificarFinalizacao(contato, totalLiquido);

        String csv = exportadorRelatorio.exportar(pedido, totalLiquido);
        return new PedidoFinalizado(totalBruto, totalLiquido, csv);
    }
}
