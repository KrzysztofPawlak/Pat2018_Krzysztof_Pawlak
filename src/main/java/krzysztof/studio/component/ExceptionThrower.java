package krzysztof.studio.component;

import krzysztof.studio.model.CustomException;

public class ExceptionThrower {

    public void throwGeneralException() throws Exception {
        Exception e = new Exception("Error from General Exception");
        throw e;
    }

    public void throwCustomException() throws CustomException {

        CustomException e = new CustomException();
        e.setCode(5);
        e.setMessage("Somthing");
        throw e;
    }
}