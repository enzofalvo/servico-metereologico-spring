package enzofalvo.clima.api.service;

import enzofalvo.clima.api.model.dto.ClimaDTOSaida;
import org.springframework.http.ResponseEntity;

public interface ClimaService {
    ClimaDTOSaida consultar(String cidade);
}