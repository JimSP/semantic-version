package br.com.cafebinario.semanticversion.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SemanticVersionTest {
	
	@Test
	public void patchWithoutPatch() {
		
		assertEquals("v1.1", SemanticVersions.next("v1.1", VersionType.PATCH));
	}
	
	@Test
	public void minnorWithoutPatch() {
		
		assertEquals("v1.2", SemanticVersions.next("v1.1", VersionType.MINNOR));
	}
	
	@Test
	public void majorWithoutPatch() {
		
		assertEquals("v2.0", SemanticVersions.next("v1.1", VersionType.MAJOR));
	}
	
	@Test
	public void majorWithoutPreRelease() {
		
		assertEquals("v2.0.0", SemanticVersions.next("v1.89.1212", VersionType.MAJOR));
	}
	
	@Test
	public void majorWithoutBuild() {
		
		assertEquals("v2.0.0-ALPHA.001", SemanticVersions.next("v1.545.45-ALPHA.001", VersionType.MAJOR));
	}
	
	@Test
	public void major() {
		
		assertEquals("v2.0.0-ALPHA.001+60", SemanticVersions.next("v1.545.45-ALPHA.001+59", VersionType.MAJOR));
	}
	
	@Test
	public void minnorWithoutPreRelease() {
		
		assertEquals("v1.1.0", SemanticVersions.next("v1.0.45", VersionType.MINNOR));
	}
	
	@Test
	public void minnorWithoutBuild() {
		
		assertEquals("v1.1.0-ALPHA.001", SemanticVersions.next("v1.0.78-ALPHA.001", VersionType.MINNOR));
	}
	
	@Test
	public void minnor() {
		
		assertEquals("v1.1.0-ALPHA.001+60", SemanticVersions.next("v1.0.45456-ALPHA.001+59", VersionType.MINNOR));
	}
	
	@Test
	public void patchWithoutPreRelease() {
		assertEquals("v1.0.1", SemanticVersions.next("v1.0.0", VersionType.PATCH));
	}
	
	@Test
	public void patchWithoutBuild() {
		
		assertEquals("v1.0.1-ALPHA.001", SemanticVersions.next("v1.0.0-ALPHA.001", VersionType.PATCH));
	}
	
	@Test
	public void patch() {
		
		assertEquals("v1.0.1-ALPHA.001+60", SemanticVersions.next("v1.0.0-ALPHA.001+59", VersionType.PATCH));
	}
	
	@Test
	public void toTagTyped() {
		
		assertEquals("v1.0.1-SNAPSHOT+60", SemanticVersions.toTag(SemanticVersions.of("v1.0.1-MILESTONE+60").toString(), TagType.SNAPSHOT));
	}
	
	@Test
	public void toTag() {
		
		assertEquals("v1.0.1-MILESTONE+60", SemanticVersions.toTag("v1.0.1-ANY.001+60", TagType.MILESTONE));
	}
	
	@Test
	public void toCustomTag() {
		
		assertEquals("v1.0.1-CUSTOM+60", SemanticVersions.toTag("v1.0.1-ANY.001+60", "CUSTOM"));
	}
}
