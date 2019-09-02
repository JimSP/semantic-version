package br.com.cafebinario.semanticversion.model;

import lombok.Getter;

@Getter
class Caracteristics {

	private final String semanticVersion;

	private final boolean containPathFragment;
	private final boolean containPreReleaseBuildFragment;
	private final boolean containBuildFragment;

	private final int lastIndexOfMajor;
	private final int lastIndexOfMinor;
	private final int lastIndexOfPath;
	private final int lastIndexOfPreReleaseSeparator;
	private final int lastIndexOfBuildSeparator;

	Caracteristics(final String semanticVersion, final boolean containPathFragment,
			final boolean containPreReleaseBuildFragment, final boolean containBuildFragment) {

		this.semanticVersion = semanticVersion;
		this.containPathFragment = containPathFragment;
		this.containPreReleaseBuildFragment = containPreReleaseBuildFragment;
		this.containBuildFragment = containBuildFragment;

		this.lastIndexOfMajor = semanticVersion.indexOf(SemanticVersion.VERSION_SEPARATOR, 0);
		this.lastIndexOfMinor = semanticVersion.indexOf(SemanticVersion.VERSION_SEPARATOR, lastIndexOfMajor + 1);
		this.lastIndexOfPath = containPathFragment
				? semanticVersion.indexOf(SemanticVersion.PRE_RELEASE_SEPARATOR, lastIndexOfMinor + 1)
				: -1;
		this.lastIndexOfPreReleaseSeparator = containPreReleaseBuildFragment
				? semanticVersion.indexOf(SemanticVersion.BUILD_SEPARATOR, lastIndexOfPath + 1)
				: -1;
		this.lastIndexOfBuildSeparator = containBuildFragment ? semanticVersion.length() : -1;
	}

	String getPatchFragment() {

		if (!isContainPathFragment()) {
			return null;
		}

		return semanticVersion.substring(lastIndexOfMinor + 1,
				containPreReleaseBuildFragment ? lastIndexOfPath : semanticVersion.length());
	}

	String getPreReleaseFragment() {

		if (!isContainPreReleaseBuildFragment()) {
			return null;
		}

		return semanticVersion.substring(lastIndexOfPath + 1,
				containBuildFragment ? lastIndexOfPreReleaseSeparator : semanticVersion.length());
	}

	String getBuildFragment() {

		if (!isContainBuildFragment()) {
			return null;
		}

		return semanticVersion.substring(lastIndexOfPreReleaseSeparator + 1, lastIndexOfBuildSeparator);
	}
}