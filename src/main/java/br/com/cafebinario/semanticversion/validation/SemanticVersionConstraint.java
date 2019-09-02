package br.com.cafebinario.semanticversion.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.cafebinario.semanticversion.exception.SemanticVersionException;
import br.com.cafebinario.semanticversion.model.SemanticVersions;

public class SemanticVersionConstraint implements ConstraintValidator<SemanticVersion, String>{

	@Override
	public boolean isValid(final String semanticVersion, final ConstraintValidatorContext context) {

		try{
			SemanticVersions.validate(semanticVersion);
			return true;
		}catch (SemanticVersionException e) {
			return false;
		}
	}
}
