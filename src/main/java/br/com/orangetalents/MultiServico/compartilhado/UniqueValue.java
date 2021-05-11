package br.com.orangetalents.MultiServico.compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    Class<?> targetClass();
    String campo();
    String message() default "Valor repetido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}