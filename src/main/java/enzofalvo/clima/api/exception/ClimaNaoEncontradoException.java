package enzofalvo.clima.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ClimaNaoEncontradoException extends RuntimeException {

    public ClimaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}