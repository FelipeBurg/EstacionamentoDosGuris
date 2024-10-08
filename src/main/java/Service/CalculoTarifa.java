package Service;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalculoTarifa {
    private double tarifaPorHora = 5.90;
    private double tarifaAdicional = 2.50;
    private double tarifaPernoite = 50.00;
    private int tempoCortesia = 15;
    private double descontoVip = 0.5;

    // Método para calcular o valor do ticket
    public double calcularValor(Duration duracao, LocalDateTime horaEntrada, LocalDateTime horaSaida, boolean isVIP) {
        long minutos = duracao.toMinutes();
        double valor;
        if (pernoitou(horaEntrada, horaSaida)) {
            valor = tarifaPernoite * numDiasPernoite(horaEntrada, horaSaida); // Valor fixo do pernoite
        } else {
            // Caso não seja pernoite, calcula valor normalmente
            if (minutos <= tempoCortesia) {
                valor = 0.0;  // 15 minutos de cortesia
            } else if (minutos <= 60) {
                valor = tarifaPorHora;  // Até 1 hora
            } else {
                long horas = duracao.toHours();
                valor = tarifaPorHora + (horas - 1) * tarifaAdicional;  // Após 1 hora, R$ 2,50 por hora adicional
            }
        }

        // Aplica desconto VIP se aplicável
        if (isVIP) {
            valor *= descontoVip;  // Desconto VIP de 50%
        }

        return valor;
    }

    public boolean pernoitou(LocalDateTime entrada, LocalDateTime saida) {
        return saida.toLocalTime().isAfter(LocalTime.of(8, 0)) && saida.toLocalDate().isAfter(entrada.toLocalDate());
    }

    public long numDiasPernoite(LocalDateTime entrada, LocalDateTime saida) {
        return saida.toLocalDate().toEpochDay() - entrada.toLocalDate().toEpochDay();
    }

}
