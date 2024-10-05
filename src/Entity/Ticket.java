package Entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String codigo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Duration tempoEstadia;
    private double valorCobrado;

    public Ticket() {
        this.codigo = UUID.randomUUID().toString();
        this.horaEntrada = LocalDateTime.now();
        this.horaSaida = null;  // A hora de saída ainda não foi registrada
        this.tempoEstadia = Duration.ZERO;
        this.valorCobrado = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public Duration getTempoEstadia() {
        return tempoEstadia;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public void registrarSaida(LocalDateTime horaSaida) {
        if (horaSaida.isBefore(horaEntrada)) {
            System.out.println("Erro: a hora de saída não pode ser anterior à hora de entrada.");
            return;
        }
        if (this.horaSaida != null) {
            System.out.println("Erro: a saída já foi registrada.");
            return;
        }
        this.horaSaida = horaSaida;  // Define a hora de saída passada como parâmetro
        this.tempoEstadia = Duration.between(horaEntrada, this.horaSaida);  // Calcula a duração entre entrada e saída
    }
}
