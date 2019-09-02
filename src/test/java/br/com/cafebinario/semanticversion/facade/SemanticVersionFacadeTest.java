package br.com.cafebinario.semanticversion.facade;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cafebinario.semanticversion.dto.SemanticVersionDTO;
import br.com.cafebinario.semanticversion.exception.SemanticVersionException;
import br.com.cafebinario.semanticversion.model.TagType;
import br.com.cafebinario.semanticversion.model.VersionType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SemanticVersionFacadeTest {

	@Autowired
	private SemanticVersionFacade semanticVersionFacade;
	
	@Test
	public void nextAndToTag() {
		
		final String version = semanticVersionFacade.nextAndToTag(
				SemanticVersionDTO
					.builder()
					.currentVersion("v1.0.0-RELEASE")
					.versionType(VersionType.MAJOR)
					.tagType(TagType.BETA)
					.build());
		
		assertThat(version).isEqualTo("v2.0.0-BETA");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nextAndToTagWithCurrentVersionBlanck() {
		
		semanticVersionFacade.nextAndToTag(
				SemanticVersionDTO
					.builder()
					.currentVersion("")
					.versionType(VersionType.MAJOR)
					.tagType(TagType.BETA)
					.build());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nextAndToTagWithCurrentVersionSpaces() {
		
		semanticVersionFacade.nextAndToTag(
				SemanticVersionDTO
					.builder()
					.currentVersion("     ")
					.versionType(VersionType.MAJOR)
					.tagType(TagType.BETA)
					.build());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void nextAndToTagWithCurrentVersionNull() {
		
		semanticVersionFacade.nextAndToTag(
				SemanticVersionDTO
					.builder()
					.currentVersion("")
					.versionType(VersionType.MAJOR)
					.tagType(TagType.BETA)
					.build());
	}
	
	@Test(expected=SemanticVersionException.class)
	public void nextAndToTagWithCurrentVersionInvalid() {
		
		semanticVersionFacade.nextAndToTag(
				SemanticVersionDTO
					.builder()
					.currentVersion("v1")
					.versionType(VersionType.MAJOR)
					.tagType(TagType.BETA)
					.build());
		
	}
	
	@Test
	public void next() {
		
		final String version = semanticVersionFacade.next(
				SemanticVersionDTO
				.builder()
				.currentVersion("v1.0.0")
				.versionType(VersionType.MAJOR)
				.build());
		
		assertThat(version).isEqualTo("v2.0.0");
	}

	@Test
	public void toTag() {
		
		final String version = semanticVersionFacade.toTag(
				SemanticVersionDTO
					.builder()
					.currentVersion("v1.0.0")
					.tagType(TagType.RELEASE)
					.build());
		
		assertThat(version).isEqualTo("v1.0.0-RELEASE");
	}
}
