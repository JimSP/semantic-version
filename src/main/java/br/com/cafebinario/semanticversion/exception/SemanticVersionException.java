package br.com.cafebinario.semanticversion.exception;

public final class SemanticVersionException extends RuntimeException{

	private static final long serialVersionUID = 1291360043746971394L;

	public SemanticVersionException(final String semanticVersion, final Throwable e) {
		super(semanticVersion, e);
	}

	
}
