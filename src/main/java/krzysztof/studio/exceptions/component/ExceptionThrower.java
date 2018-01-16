package krzysztof.studio.exceptions.component;

import krzysztof.studio.exceptions.model.AlreadyExistException;
import krzysztof.studio.exceptions.model.NotFoundException;

public class ExceptionThrower {

    public void throwGeneralException() throws Exception {
        Exception e = new Exception("Error from General Exception");
        throw e;
    }

    public void throwNotFoundException() throws NotFoundException {
        NotFoundException e = new NotFoundException();
        e.setDescription("Not Found Exception");
        throw e;
    }

    public void throwAlreadyExistException() throws Exception {
        AlreadyExistException e = new AlreadyExistException();
        e.setDescription("Already Exist Exception");
        throw e;
    }
}