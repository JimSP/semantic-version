package br.com.cafebinario.semanticversion.model;

import lombok.Getter;

@Getter
final class SemanticVersion {

	static final String VERSION_SEPARATOR = ".";
	static final String PRE_RELEASE_SEPARATOR = "-";
	static final String BUILD_SEPARATOR = "+";

	private final String majorFragment;
	private final String minnorFragment;
	private final String patchFragment;
	private final String preReleaseFragment;
	private final String buildFragment;
	private final Caracteristics caracteristics;

	SemanticVersion(
			final String[] fragments,
			final Caracteristics caracteristics) {

		this.majorFragment = fragments[0];
		this.minnorFragment = fragments[1];
		this.patchFragment = caracteristics.getPatchFragment();
		this.preReleaseFragment = caracteristics.getPreReleaseFragment();
		this.buildFragment = caracteristics.getBuildFragment();
		this.caracteristics = caracteristics;
	}

	SemanticVersion(
			final String majorFragment,
			final String minnorFragment,
			final String patchFragment,
			final String preReleaseFragment,
			final String build,
			final Caracteristics caracteristics) {
		
		this.majorFragment = majorFragment;
		this.minnorFragment = minnorFragment;
		this.patchFragment = patchFragment;
		this.preReleaseFragment = preReleaseFragment;
		this.buildFragment = build;
		this.caracteristics = caracteristics;
	}

	@Override
	public String toString() {
		
		return getMajorFragment() 
				+ VERSION_SEPARATOR 
				+ getMinnorFragment() 
				+ (getPatchFragment() != null ? VERSION_SEPARATOR + getPatchFragment() : "")
				+ (getPreReleaseFragment() != null ? PRE_RELEASE_SEPARATOR + getPreReleaseFragment() : "")
				+ (getBuildFragment() != null ? BUILD_SEPARATOR + getBuildFragment() : "");
	}
}
