package Entity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String codigo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private Duration tempoEstadia;
    private double valorTicket;

    public Ticket() {
        this.codigo = UUID.randomUUID().toString();
        this.horaEntrada = LocalDateTime.now();
        this.horaSaida = null;
        this.tempoEstadia = null;
    }

    public void setTempoEstadia(Duration tempoEstadia) {
        this.tempoEstadia = tempoEstadia;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
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

    public double getValorTicket() {
        return valorTicket;
    }
    public void setValorTicket(double valorTicket) {
        this.valorTicket = valorTicket;
    }
}