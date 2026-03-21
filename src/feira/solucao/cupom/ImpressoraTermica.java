package feira.solucao.cupom;

import feira.solucao.domain.Pedido;
import feira.solucao.domain.PedidoItem;

public class ImpressoraTermica implements ImpressoraCupom {
    @Override
    public void imprimir(Pedido pedido, double totalLiquido) {
        System.out.println("=== CUPOM ===");
        System.out.println("Cliente: " + pedido.getCliente());
        for (PedidoItem item : pedido.getItens()) {
            System.out.println(item.getProduto().getNome() + " x" + item.getQuantidade() + " = R$ " + item.subtotal());
        }
        System.out.println("Total líquido: R$ " + totalLiquido);
        System.out.println("=============");
    }
}