package enzofalvo.clima.api.controller;

import enzofalvo.clima.api.model.dto.ClimaDTOSaida;
import enzofalvo.clima.api.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clima")
public class ClimaController {

    private ClimaService climaService;

    @Autowired
    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @GetMapping("/{local}")
    public ResponseEntity<ClimaDTOSaida> consultar(@PathVariable String local) {
        return ResponseEntity.ok(climaService.consultar(local));
    }
}