package br.com.cafebinario.semanticversion.model;

import org.springframework.util.Assert;

import br.com.cafebinario.semanticversion.exception.SemanticVersionException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class SemanticVersions {
	
	private static final int MIN_PATCH_FRAGMENT_LEN = 2;
	private static final String SCAPE = "\\";

	public static void validate(final String semanticVersion) {

		SemanticVersions.of(semanticVersion);
	}

	public static SemanticVersion of(final String semanticVersion) {

		try {
			
			final String[] fragments = semanticVersion
					.split("[" + SemanticVersion.VERSION_SEPARATOR + SCAPE + SemanticVersion.PRE_RELEASE_SEPARATOR + SCAPE + SemanticVersion.BUILD_SEPARATOR + "]");

			final boolean containPathFragment = fragments.length > MIN_PATCH_FRAGMENT_LEN;
			final boolean containPreReleaseBuildFragment = semanticVersion.contains(SemanticVersion.PRE_RELEASE_SEPARATOR);
			final boolean containBuildFragment = semanticVersion.contains(SemanticVersion.BUILD_SEPARATOR);

			final Caracteristics caracteristics = new Caracteristics(semanticVersion, containPathFragment,
					containPreReleaseBuildFragment, containBuildFragment);

			return new SemanticVersion(fragments, caracteristics);
		} catch (Exception e) {
			
			log.error("Invalid semanticVersion value: {}", semanticVersion, e);
			throw new SemanticVersionException(semanticVersion, e);
		}
	}

	public static SemanticVersion next(final SemanticVersion currentVersion, final VersionType versionType) {

		return versionType.next(currentVersion);
	}

	public static String next(final String currentVersion, final VersionType versionType) {

		Assert.hasText(currentVersion, "'currentVersion' must not be empty");
		Assert.hasText(currentVersion, "'versionType' must not be empty");
		
		return SemanticVersions.next(SemanticVersions.of(currentVersion), versionType).toString();
	}

	public static String toTag(final String semanticVersion, final TagType tagType) {
		
		Assert.hasText(semanticVersion, "'semanticVersion' must not be empty");
		Assert.notNull(tagType, "The TagType must not be null");

		return tagType.toTag(SemanticVersions.of(semanticVersion)).toString();
	}

	public static String toTag(String semanticVersion, String customTag) {
		
		Assert.hasText(semanticVersion, "'semanticVersion' must not be empty");
		Assert.hasText(customTag, "'customTag' must not be empty");

		return TagType.toTag(SemanticVersions.of(semanticVersion), customTag).toString();
	}
	
	private SemanticVersions() {
		
	}
}
