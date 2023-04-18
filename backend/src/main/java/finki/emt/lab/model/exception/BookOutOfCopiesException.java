package finki.emt.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookOutOfCopiesException extends RuntimeException{
    public BookOutOfCopiesException(String name) {
        super(String.format("Book with name: %s has no copies left",name));
    }
}
