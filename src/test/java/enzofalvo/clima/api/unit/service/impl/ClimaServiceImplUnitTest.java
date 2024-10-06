package enzofalvo.clima.api.unit.service.impl;

import enzofalvo.clima.api.model.dto.ClimaDTOSaida;
import enzofalvo.clima.api.service.impl.ClimaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unitário: Testes da classe service do clima")
class ClimaServiceImplUnitTest {

    private ClimaServiceImpl climaService;

    @BeforeEach
    void setUp() {
        this.climaService = new ClimaServiceImpl();
    }

    @Test
    @DisplayName("Ao consultar um local inexistente, deve retornar status 400")
    void aoConsultarUmInexistenteDeveRetornarStatus400() {
        climaService.consultar("Amatiba");
    }
}
