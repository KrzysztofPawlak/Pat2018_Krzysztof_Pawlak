package krzysztof.studio.exceptions;

import krzysztof.studio.exceptions.model.AlreadyExistException;
import krzysztof.studio.exceptions.model.ExceptionResponse;
import krzysztof.studio.exceptions.model.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> generalException(Exception e) throws Exception {

        ExceptionResponse eR = new ExceptionResponse();
        eR.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        eR.setDescription(e.getMessage());

        return new ResponseEntity<ExceptionResponse>(eR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException e) throws Exception {

        ExceptionResponse eR = new ExceptionResponse();
        eR.setCode(HttpStatus.NOT_FOUND.value());
        eR.setDescription(e.getDescription());

        return new ResponseEntity<ExceptionResponse>(eR, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> alreadyExistException(AlreadyExistException e) throws Exception {

        ExceptionResponse eR = new ExceptionResponse();
        eR.setCode(HttpStatus.CONFLICT.value());
        eR.setDescription(e.getDescription());

        return new ResponseEntity<ExceptionResponse>(eR, HttpStatus.CONFLICT);
    }
}