package enzofalvo.clima.api.exception.handler;

import enzofalvo.clima.api.exception.ClimaNaoEncontradoException;
import enzofalvo.clima.api.exception.ErroNaoEsperadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClimaExceptionHandler {

    @ExceptionHandler(ClimaNaoEncontradoException.class)
    public ResponseEntity<String> climaNaoEncontrado(ClimaNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ErroNaoEsperadoException.class)
    public ResponseEntity<String> erroNaoEsperado(ErroNaoEsperadoException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}