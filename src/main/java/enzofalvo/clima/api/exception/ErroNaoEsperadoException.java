package enzofalvo.clima.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ErroNaoEsperadoException extends RuntimeException {
    public ErroNaoEsperadoException(String mensagem) {
        super(mensagem);
    }
}