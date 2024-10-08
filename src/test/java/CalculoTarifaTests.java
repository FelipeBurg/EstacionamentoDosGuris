import Service.CalculoTarifa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CalculoTarifaTests {
    private CalculoTarifa ct = null;

    @BeforeEach
    void setUp() {
        ct = new CalculoTarifa();
    }

    @Test
    void IsOutroDiaTrue() {
        /*setando um horário de teste base, como esse teste foi feito às 2:20
        eu retiro 2 horas desse horário pra cumprir uma das condições do isOutroDia*/
        LocalDateTime entrada = LocalDateTime.now().minusHours(2);

        /* O mesmo vale para o horário de saída, adiciono mais um dia e mais 9 horas,
        só pra garantir mesmo*/
        LocalDateTime saida = LocalDateTime.now().plusDays(1).withHour(9);

        /*esse booleano é pra poder fazer a asserção, facilita na hora de passar os parâmetros*/
        boolean verifica = ct.isOutroDia(entrada, saida);

        /* a asserção foi feita, o valor esperado do teste é "true" e o valor que será
        comparado será o de "verifica"*/
        assertEquals(true, verifica);
    }

    @Test
    void IsOutroDiaFalse_SaidaMesmoDia() {
        LocalDateTime horaEntrada = LocalDateTime.of(2023, 10, 7, 1, 30);
        LocalDateTime horaSaida = LocalDateTime.of(2023, 10, 7, 7, 30);

        boolean verifica = ct.isOutroDia(horaEntrada, horaSaida);
        assertFalse(verifica);
    }

    @Test
    public void testCalcularValorPernoite() {
        LocalDateTime horaEntrada = LocalDateTime.of(2023, 10, 7, 1, 30);
        LocalDateTime horaSaida = LocalDateTime.of(2023, 10, 8, 9, 0);
        Duration duracao = Duration.between(horaEntrada, horaSaida);

        double valor = ct.calcularValor(duracao, horaEntrada, horaSaida, false);

        assertEquals(50.00, valor, 0.01);
    }

    @Test
    public void testCalcularValorMaisDeUmaHoraComVIP() {
        LocalDateTime horaEntrada = LocalDateTime.now();
        LocalDateTime horaSaida = horaEntrada.plusHours(2);
        Duration duracao = Duration.between(horaEntrada, horaSaida);

        double valor = ct.calcularValor(duracao, horaEntrada, horaSaida, true);

        assertEquals((5.90 + 2.50) * 0.5, valor, 0.01);
    }
}