package Entity;

import java.time.LocalTime;
import java.util.ArrayList;

public class Estacionamento {
    private String nome;
    private LocalTime abertura;
    private LocalTime fechamento;
    private ArrayList<Automovel> autos;

    public Estacionamento() {
        this.nome = "Estacionamento dos Guri";
        this.abertura = LocalTime.of(8, 0);  // 08:00
        this.fechamento = LocalTime.of(2, 0); // 02:00 (2 da manhã do dia seguinte)
        this.autos = new ArrayList<>(); // Inicializa a lista de automóveis
    }
    public LocalTime getAbertura() {
        return abertura;
    }
    public void adicionarAutomovel(Automovel automovel) {
        autos.add(automovel);
    }

    public Automovel buscarAutomovelPorPlaca(String placa) {
        for (Automovel automovel : autos) {
            if (automovel.getPlaca().equalsIgnoreCase(placa)) {
                return automovel;  // Retorna o automóvel se a placa for encontrada
            }
        }
        return null;  // Retorna null se não encontrar
    }


}
