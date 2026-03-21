package feira.solucao.entrega;

public interface CalculadoraPrazoEntrega {
    String tipo();

    int calcularPrazoDias(double distanciaKm);
}