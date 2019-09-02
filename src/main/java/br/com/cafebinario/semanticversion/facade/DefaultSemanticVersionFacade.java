package br.com.cafebinario.semanticversion.facade;

import org.springframework.stereotype.Service;

import br.com.cafebinario.semanticversion.dto.SemanticVersionDTO;
import br.com.cafebinario.semanticversion.model.SemanticVersions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultSemanticVersionFacade implements SemanticVersionFacade{

	@Override
	public String nextAndToTag(final SemanticVersionDTO semanticVersionDTO) {
		
		log.info("m=nextAndToTag, semanticVersionDTO={}", semanticVersionDTO);
		
		final String nextVersion = next(semanticVersionDTO);
		
		log.debug("m=nextAndToTag, semanticVersionDTO={}, nextVersion={}", semanticVersionDTO, nextVersion);
		
		return toTag(semanticVersionDTO
				.toBuilder()
				.currentVersion(nextVersion)
				.build());
	}

	@Override
	public String next(final SemanticVersionDTO semanticVersionDTO) {
		
		log.info("m=next, semanticVersionDTO={}", semanticVersionDTO);
		
		return SemanticVersions.next(
				semanticVersionDTO.getCurrentVersion(), 
				semanticVersionDTO.getVersionType());
	}
	
	@Override
	public String toTag(final SemanticVersionDTO semanticVersionDTO) {
		
		log.info("m=toTag, semanticVersionDTO={}", semanticVersionDTO);
		
		return SemanticVersions.toTag(
				semanticVersionDTO.getCurrentVersion(), 
				semanticVersionDTO.getTagType());
	}
}
