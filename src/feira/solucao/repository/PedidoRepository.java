package feira.solucao.repository;

import feira.solucao.domain.Pedido;
import java.util.List;

public interface PedidoRepository {
    void salvar(Pedido pedido);

    List<Pedido> listarTodos();
}