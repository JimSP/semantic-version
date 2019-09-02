package br.com.cafebinario.semanticversion.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cafebinario.semanticversion.dto.SemanticVersionDTO;
import br.com.cafebinario.semanticversion.facade.SemanticVersionFacade;
import br.com.cafebinario.semanticversion.model.TagType;
import br.com.cafebinario.semanticversion.model.VersionType;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SemanticVersionRestController {

	@Autowired
	private SemanticVersionFacade semanticVersionFacade;
	
	@GetMapping(path = "next/{currentVersion}/{versionType}/{tagType}")
	public String nextAndToTag(
			@Valid final SemanticVersionDTO semanticVersionDTO) {

		log.info("m=nextAndToTag, semanticVersionDTO={}", semanticVersionDTO);
		
		return semanticVersionFacade.nextAndToTag(semanticVersionDTO);
	}

	@GetMapping(path = "version/{currentVersion}/{versionType}")
	public String version(@Valid final SemanticVersionDTO semanticVersionDTO) {

		log.info("m=version, semanticVersionDTO={}", semanticVersionDTO);
		
		return semanticVersionFacade.next(semanticVersionDTO);
	}

	@GetMapping(path = "tag/{currentVersion}/{tagType}")
	public String tag(@Valid final SemanticVersionDTO semanticVersionDTO) {

		log.info("m=tag, semanticVersionDTO={}", semanticVersionDTO);
		
		return semanticVersionFacade.toTag(semanticVersionDTO);
	}

	@GetMapping(path = "version")
	public VersionType[] version() {

		log.info("m=version");
		
		return VersionType.values();
	}

	@GetMapping(path = "tag")
	public TagType[] tag() {

		log.info("m=tag");
		
		return TagType.values();
	}
}
