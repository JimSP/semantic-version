package br.com.cafebinario.semanticversion.model;

public enum VersionType {

	PATCH {

		@Override
		public SemanticVersion next(final SemanticVersion currentVersion) {

			return new SemanticVersion(
					currentVersion.getMajorFragment(),
					currentVersion.getMinnorFragment(),
					currentVersion.getCaracteristics().isContainPathFragment() ? addFragment(currentVersion.getPatchFragment()) : null,
							currentVersion.getCaracteristics().isContainPreReleaseBuildFragment() ? currentVersion.getPreReleaseFragment() : null,
							currentVersion.getCaracteristics().isContainBuildFragment() ? increment(currentVersion.getBuildFragment()) : null,
					null);
		}

	},
	MINNOR {

		@Override
		public SemanticVersion next(final SemanticVersion currentVersion) {

			return new SemanticVersion(
					currentVersion.getMajorFragment(),
					addFragment(currentVersion.getMinnorFragment()),
					currentVersion.getCaracteristics().isContainPathFragment() ? ZERO : null,
							currentVersion.getCaracteristics().isContainPreReleaseBuildFragment() ? currentVersion.getPreReleaseFragment() : null,
							currentVersion.getCaracteristics().isContainBuildFragment() ? increment(currentVersion.getBuildFragment()) : null,
					null);
		}

	},
	MAJOR {

		@Override
		public SemanticVersion next(final SemanticVersion currentVersion) {

			return new SemanticVersion(
					addFragment(currentVersion.getMajorFragment()),
					ZERO,
					currentVersion.getCaracteristics().isContainPathFragment() ? ZERO : null,
					currentVersion.getCaracteristics().isContainPreReleaseBuildFragment() ? currentVersion.getPreReleaseFragment() : null,
					currentVersion.getCaracteristics().isContainBuildFragment() ? increment(currentVersion.getBuildFragment()) : null,
					null);
		}

	};

	private static final String ZERO = "0";

	public abstract SemanticVersion next(final SemanticVersion currentVersion);

	static String addFragment(final String fragment) {

		final int lastedDigitIndex = fragment.length() - 1;

		final int lastedDigit = (int) fragment.charAt(lastedDigitIndex);

		final int nextLestedDigit = (lastedDigit % 64) + 1;

		return fragment.substring(0, lastedDigitIndex) + (char) nextLestedDigit;
	}

	private static String increment(final String buildFragment) {

		return String.valueOf(Integer.parseInt(buildFragment) + 1);
	}

}
