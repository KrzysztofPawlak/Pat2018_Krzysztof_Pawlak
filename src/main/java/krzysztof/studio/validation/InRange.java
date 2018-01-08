package krzysztof.studio.validation;

import javax.validation.Payload;

public @interface InRange {
    String message() default "value is out of range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payLoad() default {};
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}