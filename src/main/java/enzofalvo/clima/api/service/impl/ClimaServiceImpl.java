package enzofalvo.clima.api.service.impl;

import enzofalvo.clima.api.exception.ClimaNaoEncontradoException;
import enzofalvo.clima.api.model.dto.ClimaDTOSaida;
import enzofalvo.clima.api.service.ClimaService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClimaServiceImpl implements ClimaService {
    @Override
    public ClimaDTOSaida consultar(String cidade) {
        String resultado = "";
        if (resultado.isEmpty()) {
            throw new ClimaNaoEncontradoException("Não existe um resultado para o local escolhido");
        }
        return ClimaDTOSaida.builder().temperatura(10).build();
    }
}
