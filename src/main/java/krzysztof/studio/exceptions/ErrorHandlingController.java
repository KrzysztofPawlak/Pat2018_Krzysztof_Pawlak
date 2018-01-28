package krzysztof.studio.exceptions;

import krzysztof.studio.exceptions.model.AlreadyExistException;
import krzysztof.studio.exceptions.model.ExceptionResponse;
import krzysztof.studio.exceptions.model.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

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

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidInput(ConstraintViolationException ex) {

        Set violations = ex.getConstraintViolations();
        ConstraintViolation v = (ConstraintViolation) violations.toArray()[0];

        ExceptionResponse eR = new ExceptionResponse();
        eR.setCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        eR.setDescription("błąd danych wejściowych " + "w parametrze: " + v.getPropertyPath() + ". " + v.getMessage());

        return new ResponseEntity(eR, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}