package br.com.cafebinario.semanticversion.facade;

import br.com.cafebinario.semanticversion.dto.SemanticVersionDTO;

public interface SemanticVersionFacade {

	String nextAndToTag(final SemanticVersionDTO semanticVersionDTO);
	String next(final SemanticVersionDTO semanticVersionDTO);
	String toTag(final SemanticVersionDTO semanticVersionDTO);
}
