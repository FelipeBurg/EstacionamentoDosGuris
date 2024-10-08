import Service.CalculoTarifa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
class CalculoTarifaTests {
    private CalculoTarifa ct = null;

    @BeforeEach
    void setUp() {
        ct = new CalculoTarifa();
    }

    @Test
    void testCalcularValorPernoiteNaoVip() {
        LocalDateTime horaEntrada = LocalDateTime.of(2023, 10, 7, 1, 30);
        LocalDateTime horaSaida = LocalDateTime.of(2023, 10, 8, 9, 0);
        Duration duracao = Duration.between(horaEntrada, horaSaida);

        double valor = ct.calcularValor(duracao, horaEntrada, horaSaida, false);

        assertEquals(50.00, valor, 0.01);
    }
    @Test
    void testCalcularValorPernoiteVip() {
        LocalDateTime horaEntrada = LocalDateTime.of(2023, 10, 7, 1, 30);
        LocalDateTime horaSaida = LocalDateTime.of(2023, 10, 8, 9, 0);
        Duration duracao = Duration.between(horaEntrada, horaSaida);

        double valor = ct.calcularValor(duracao, horaEntrada, horaSaida, true);

        assertEquals(25.00, valor, 0.01);
    }

    @Test
    void testCalcularValorMaisDeUmaHoraComVIP() {
        LocalDateTime horaEntrada = LocalDateTime.now();
        LocalDateTime horaSaida = horaEntrada.plusHours(2);
        Duration duracao = Duration.between(horaEntrada, horaSaida);

        double valor = ct.calcularValor(duracao, horaEntrada, horaSaida, true);

        assertEquals((5.90 + 2.50) * 0.5, valor, 0.01);
    }

    @Test
    void PernoitouRetornaTrue(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusDays(1);

        boolean verifica = ct.pernoitou(entrada, saida);
        assertEquals(true, verifica);
    }

    @Test
    void ValorCortesiaComMenosDe15Minutos(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusMinutes(10);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, false);

        assertEquals(0.0, valor);
    }

    @Test
    void ValorCortesiaApos15Minutos(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusMinutes(16);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, false);

        assertEquals(5.90, valor);
    }

    @Test
    void ValorCortesiaCom15Minutos(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusMinutes(15);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, true);

        assertEquals(0.0, valor);
    }
    @Test
    void ValorComMenosDe1HoraDePermanenciaNaoVip(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, false);

        assertEquals(5.90, valor);
    }
    @Test
    void ValorCom1HoraDePermanenciaNaoVip(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusHours(1);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, false);

        assertEquals(5.90, valor);
    }
    @Test
    void ValorComMaisDe1HoraDePermanenciaNaoVip(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusHours(2).plusMinutes(30);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, false);

        assertEquals(8.40, valor);
    }
    @Test
    void ValorComMenosDe1HoraDePermanenciaVip(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusMinutes(30);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, true);

        assertEquals(2.95, valor);
    }

    @Test
    void ValorCom1HoraDePermanenciaVip(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusHours(1);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, true);

        assertEquals(2.95, valor);
    }
    @Test
    void ValorComMaisDe1HoraDePermanenciaVip(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusHours(2);

        Duration duracao = Duration.between(entrada, saida);
        double valor = ct.calcularValor(duracao, entrada, saida, true);

        assertEquals(4.2, valor);
    }
}