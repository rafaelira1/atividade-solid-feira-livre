package feira.solucao.notificacao;

public interface NotificadorPedido {
    void notificarFinalizacao(String contato, double total);
}
