package br.com.cafebinario.semanticversion.model;

public enum TagType {

	ALPHA, BETA, SNAPSHOT, MILESTONE, RELEASE;

	public SemanticVersion toTag(final SemanticVersion semanticVersion) {
		return new SemanticVersion(
				semanticVersion.getMajorFragment(),
				semanticVersion.getMinnorFragment(),
				semanticVersion.getPatchFragment(),
				this.name(),
				semanticVersion.getBuildFragment(),
				semanticVersion.getCaracteristics());
	}
	
	public static SemanticVersion toTag(final SemanticVersion semanticVersion, final String customTag) {
		return new SemanticVersion(
				semanticVersion.getMajorFragment(),
				semanticVersion.getMinnorFragment(),
				semanticVersion.getPatchFragment(),
				customTag,
				semanticVersion.getBuildFragment(),
				semanticVersion.getCaracteristics());
	}
}
