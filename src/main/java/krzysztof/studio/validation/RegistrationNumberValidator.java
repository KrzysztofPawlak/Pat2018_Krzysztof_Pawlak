package krzysztof.studio.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class RegistrationNumberValidator implements ConstraintValidator<RegistrationNumber, String> {


    Pattern upperCase = Pattern.compile("[A-Z]*");
    Pattern digitCase = Pattern.compile("[0-9]*");
    Pattern repeat = Pattern.compile("([A-Z]*)\\1$");

    @Override
    public void initialize(RegistrationNumber constrainAnnotation) {

    }

    @Override
    public boolean isValid(String stringToValid, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = false;

        String characters = stringToValid.substring(0, 2);
        String digits = stringToValid.substring(2, stringToValid.length());

        if (upperCase.matcher(characters).matches() && digitCase.matcher(digits).matches()
                && !repeat.matcher(characters).matches()) {
            result = true;
        }

        return result;
    }
}