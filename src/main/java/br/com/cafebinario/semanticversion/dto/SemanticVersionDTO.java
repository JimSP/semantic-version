package br.com.cafebinario.semanticversion.dto;

import br.com.cafebinario.semanticversion.model.TagType;
import br.com.cafebinario.semanticversion.model.VersionType;
import br.com.cafebinario.semanticversion.validation.SemanticVersion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
public final class SemanticVersionDTO {

	@SemanticVersion 
	private String currentVersion;
	
	private VersionType versionType;
	
	private TagType tagType;
}
