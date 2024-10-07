package Entity;

import java.time.LocalTime;
import java.util.ArrayList;

public class Estacionamento {
    private LocalTime abertura;
    private LocalTime fechamento;
    private ArrayList<Automovel> autos;

    public Estacionamento(String nome, LocalTime abertura, LocalTime fechamento) {
        this.abertura = null;
        this.fechamento = null;
        this.autos = new ArrayList<Automovel>();
    }

    public void adicionarAutomovel(Automovel automovel) {
        autos.add(automovel);
    }
    public ArrayList<Automovel> getAutomoveis() {
        return autos;
    }
    public LocalTime getAbertura() {
        return abertura;
    }
    public LocalTime getFechamento() {
        return fechamento;
    }
    public Automovel buscarPorPlaca(String placa) {
        for (Automovel automovel : autos) {
            if (automovel.getPlaca().equals(placa)) {
                return automovel;
            }
        }
        return null;
    }
}