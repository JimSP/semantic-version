package br.com.cafebinario.semanticversion.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SemanticVersionConstraint.class)
@Documented
public @interface SemanticVersion {

	String message() default "{br.com.cafebinario.semanticversio.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
		SemanticVersion[] value();
    }
}
