package Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CalculoTarifa {

    // Método para calcular o valor do ticket
    public double calcularValor(Duration duracao, LocalDateTime horaEntrada, LocalDateTime horaSaida, boolean isVIP) {
        long minutos = duracao.toMinutes();
        double valor;
        LocalDateTime horaEntradaSaida = horaEntrada;
        LocalDateTime horaSaidaSaida = horaSaida;
        if (isOutroDia(horaEntradaSaida, horaSaida)) {
            valor = 50.00; // Valor fixo do pernoite
        } else {
            // Caso não seja pernoite, calcula valor normalmente
            if (minutos <= 15) {
                valor = 0.0;  // 15 minutos de cortesia
            } else if (minutos <= 60) {
                valor = 5.90;  // Até 1 hora
            } else {
                long horas = duracao.toHours();
                valor = 5.90 + (horas - 1) * 2.50;  // Após 1 hora, R$ 2,50 por hora adicional
            }
        }

        // Aplica desconto VIP se aplicável
        if (isVIP) {
            valor *= 0.5;  // Desconto VIP de 50%
        }

        return valor;
    }

    public boolean isOutroDia(LocalDateTime horaEntrada, LocalDateTime horaSaida) {
        // Considera-se pernoite se a entrada for antes das 2h e a saída for após as 8h do dia seguinte
        LocalDateTime limiteSaida = horaEntrada.plusDays(1).withHour(8).withMinute(0).withSecond(0);
        if (horaEntrada.getHour() < 2 && horaSaida.isAfter(limiteSaida)){
            return true;
        }else {
            return false;
        }
    }

}
