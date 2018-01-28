package krzysztof.studio.exceptions.component;

import krzysztof.studio.exceptions.model.AlreadyExistException;
import krzysztof.studio.exceptions.model.NotFoundException;

public class ExceptionThrower {

    public void throwNotFoundException() throws NotFoundException {
        NotFoundException e = new NotFoundException();
        e.setDescription("Błąd. Nie znaleziono.");
        throw e;
    }

    public void throwAlreadyExistException() throws Exception {
        AlreadyExistException e = new AlreadyExistException();
        e.setDescription("Błąd. Element istnieje w bazie.");
        throw e;
    }
}