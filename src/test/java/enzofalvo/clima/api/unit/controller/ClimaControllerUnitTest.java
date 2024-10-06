package enzofalvo.clima.api.unit.controller;

import enzofalvo.clima.api.controller.ClimaController;
import enzofalvo.clima.api.exception.ClimaNaoEncontradoException;
import enzofalvo.clima.api.exception.ErroNaoEsperadoException;
import enzofalvo.clima.api.model.dto.ClimaDTOSaida;
import enzofalvo.clima.api.service.ClimaService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("Unitário: Testes da classe controladora do clima")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClimaControllerUnitTest {
    @Mock
    private ClimaService climaService;

    @LocalServerPort
    private int port;

    private ClimaController climaController;

    @BeforeEach
    void setUp() {
        openMocks(this);
        climaController = new ClimaController(climaService);
    }

    @Test
    @DisplayName("Ao consultar o clima de uma cidade, deve retornar resultado não nulo")
    void aoConsultarOClimaDeUmaCidadeDeveRetornarResultadoNaoNulo() {
        Mockito.when(climaService.consultar(any())).thenReturn(ClimaDTOSaida.builder().temperatura(12).build());
        assertNotNull(climaController.consultar("Curitiba").getBody());
    }

    @Test
    @DisplayName("Ao consultar o clima de uma cidade, deve retornar status 200 ok")
    void aoConsultarOClimaDeUmaCidadeDeveRetornarStatus200ok() {
        assertEquals(HttpStatus.OK, climaController.consultar("Curitiba").getStatusCode());
    }

    @Test
    @DisplayName("Ao consultar o clima de um estado, deve retornar resultado não nulo")
    void aoConsultarOClimaDeUmEstadoDeveRetornarResultadoNaoNulo() {
        Mockito.when(climaService.consultar(any())).thenReturn(ClimaDTOSaida.builder().temperatura(10).build());
        assertNotNull(climaController.consultar("Paraná").getBody());
    }

    @Test
    @DisplayName("Ao consultar o clima de um estado, deve retornar status 200 ok")
    void aoConsultarOClimaDeUmEstadoDeveRetornarStatus200ok() {
        assertEquals(HttpStatus.OK, climaController.consultar("Paraná").getStatusCode());
    }

    @Test
    @DisplayName("Ao consultar o clima e não for encontrado resultado, deve retornar status de não encontrado")
    void aoConsultarOClimaENaoForEncontradoResultadoDeveRetornarStatusDeNaoEncontrado() {
        Mockito.when(climaService.consultar(any())).thenThrow(new ClimaNaoEncontradoException("Não existe um resultado para o local escolhido"));

        RestAssured.port = port;

        Response response = given()
                .when()
                .get("/clima/Paraná")
                .then()
                .extract()
                .response();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    @DisplayName("Ao consultar o clima e não for encontrado resultado, deve retornar exceção personalizada")
    void aoConsultarOClimaENaoForEncontradoResultadoDeveRetornarExcecaoPersonalizada() {
        Mockito.when(climaService.consultar(any())).thenThrow(new ClimaNaoEncontradoException("Não existe um resultado para o local escolhido"));
        assertThrows(ClimaNaoEncontradoException.class, () -> climaController.consultar("Paraná"));
    }

    @Test
    @DisplayName("Ao consultar o clima e ocorrer um erro durante o processo, deve retornar exceção personalizada")
    void aoConsultarOClimaEOcorrerUmErroDuranteOProcessoDeveRetornarExcecaoPersonalizada() {
        Mockito.when(climaService.consultar(any())).thenThrow(new ErroNaoEsperadoException("Ocorreu um inesperado ao consultar o clima de Paraná"));
        assertThrows(ErroNaoEsperadoException.class, () -> climaController.consultar("Paraná"));
    }
}