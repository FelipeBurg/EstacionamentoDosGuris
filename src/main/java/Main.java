import Entity.Estacionamento;
import Service.Aplicacao;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento("Meu Entity.Estacionamento", LocalTime.of(8, 0), LocalTime.of(22, 0));
        Aplicacao app = new Aplicacao(estacionamento);
        app.menu();

    }
}