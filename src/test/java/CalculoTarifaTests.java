import Service.CalculoTarifa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CalculoTarifaTests {
    private CalculoTarifa ct = null;

    @BeforeEach
    void setUp() {
        ct = new CalculoTarifa();
    }

    @Test
    void IsOutroDiaTrue(){
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = LocalDateTime.now().plusDays(1);
        boolean verifica = ct.isOutroDia(entrada, saida);
        Assertions.assertEquals(true, verifica);
    }

}