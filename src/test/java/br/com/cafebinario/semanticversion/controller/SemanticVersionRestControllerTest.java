package br.com.cafebinario.semanticversion.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.cafebinario.semanticversion.dto.SemanticVersionDTO;
import br.com.cafebinario.semanticversion.facade.SemanticVersionFacade;
import br.com.cafebinario.semanticversion.model.TagType;
import br.com.cafebinario.semanticversion.model.VersionType;

@RunWith(SpringRunner.class)
@WebMvcTest(SemanticVersionRestController.class)
public class SemanticVersionRestControllerTest {

	private static final String NEXT_CURRENT_VERSION_VERSION_TYPE_TAG_TYPE = "/next/{currentVersion}/{versionType}/{tagType}";
	private static final String VERSION_CURRENT_VERSION_VERSION_TYPE = "/version/{currentVersion}/{versionType}";
	private static final String TAG_CURRENT_VERSION_TAG_TYPE = "/tag/{currentVersion}/{tagType}";
	private static final String VERSION = "/version";
	private static final String TAG = "/tag";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SemanticVersionFacade semanticVersionFacade;
	
	private String currentVersion;
	
	private VersionType versionType;
	
	private TagType tagType;
	
	private String result;
	
	@Test
	public void next() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "1.0.0-BETA+0";
		this.versionType = VersionType.MAJOR;
		this.tagType = TagType.RELEASE;
		
		final String expected = "2.0.0-RELEASE+1";
		
		Mockito
			.when(semanticVersionFacade
					.nextAndToTag(SemanticVersionDTO
							.builder()
							.currentVersion(currentVersion)
							.versionType(versionType)
							.tagType(tagType)
							.build()))
			.thenReturn(expected);
		
		this.result = mockMvc
			.perform(get(NEXT_CURRENT_VERSION_VERSION_TYPE_TAG_TYPE, currentVersion, versionType, tagType))
			.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void nextCurrentVersionWithBlanck() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "";
		this.versionType = VersionType.MAJOR;
		this.tagType = TagType.RELEASE;
		
		mockMvc
			.perform(get(NEXT_CURRENT_VERSION_VERSION_TYPE_TAG_TYPE, currentVersion, versionType, tagType))
			.andExpect(status().isNotFound());
	}

	@Test
	public void nextVersionTypeWithBlanck() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "1.0.0-BETA+0";
		this.versionType = null;
		this.tagType = TagType.RELEASE;
		
		mockMvc
			.perform(get(NEXT_CURRENT_VERSION_VERSION_TYPE_TAG_TYPE, currentVersion, versionType, tagType))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void nextTagTypeWithBlanck() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "1.0.0-BETA+0";
		this.versionType = VersionType.MAJOR;
		this.tagType = null;
		
		mockMvc
			.perform(get(NEXT_CURRENT_VERSION_VERSION_TYPE_TAG_TYPE, currentVersion, versionType, tagType))
			.andExpect(status().isNotFound());
	}

	@Test
	public void nextInvalidCurrentVersion() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "v1";
		this.versionType = VersionType.MAJOR;
		this.tagType = TagType.RELEASE;
		
		mockMvc
			.perform(get(NEXT_CURRENT_VERSION_VERSION_TYPE_TAG_TYPE, currentVersion, versionType, tagType))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void version() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "1.0.0-BETA+0";
		this.versionType = VersionType.MAJOR;
		
		final String expected = "2.0.0-BETA+1";
		
		Mockito
			.when(semanticVersionFacade
					.next(SemanticVersionDTO
							.builder()
							.currentVersion(currentVersion)
							.versionType(versionType)
							.build()))
			.thenReturn(expected);
		
		this.result = mockMvc
			.perform(get(VERSION_CURRENT_VERSION_VERSION_TYPE, currentVersion, versionType))
			.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		
		assertThat(result).isEqualTo(expected);
	}

	@Test
	public void versionCurrentVersionWithBlanck() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "";
		this.versionType = VersionType.MAJOR;
		
		mockMvc
			.perform(get(VERSION_CURRENT_VERSION_VERSION_TYPE, currentVersion, versionType))
			.andExpect(status().isNotFound());
	}

	@Test
	public void versionVersionTypeWithBlanck() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "1.0.0-BETA+0";
		this.versionType = null;
		
		mockMvc
			.perform(get(VERSION_CURRENT_VERSION_VERSION_TYPE, currentVersion, versionType))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void versionInvalidVersionType() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "1.0.0-BETA+0";
		
		mockMvc
			.perform(get(VERSION_CURRENT_VERSION_VERSION_TYPE, currentVersion, "ANY"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void tag() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "1.0.0-BETA+0";
		this.tagType = TagType.RELEASE;
		
		final String expected = "1.0.0-RELEASE+0";
		
		Mockito
			.when(semanticVersionFacade
					.toTag(SemanticVersionDTO
							.builder()
							.currentVersion(currentVersion)
							.tagType(tagType)
							.build()))
			.thenReturn(expected);
		
		this.result = mockMvc
			.perform(get(TAG_CURRENT_VERSION_TAG_TYPE, currentVersion, tagType))
			.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		
		assertThat(result).isEqualTo("1.0.0-RELEASE+0");
	}

	@Test
	public void tagWithBlanck() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "";
		this.tagType = TagType.RELEASE;
		
		mockMvc
			.perform(get(TAG_CURRENT_VERSION_TAG_TYPE, currentVersion, tagType))
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void tagWithTagTypeBlanck() throws UnsupportedEncodingException, Exception {
		
		this.currentVersion = "1.0.0-RELEASE+0";
		this.tagType = null;
		
		mockMvc
			.perform(get(TAG_CURRENT_VERSION_TAG_TYPE, currentVersion, tagType))
			.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void listVersions() throws Exception {
		
		result = mockMvc
			.perform(get(VERSION))
			.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		
		assertThat(result).isEqualTo("[\"PATCH\",\"MINNOR\",\"MAJOR\"]");
	}

	@Test
	public void listTags() throws UnsupportedEncodingException, Exception {

		result = mockMvc
				.perform(get(TAG))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
			
			assertThat(result).isEqualTo("[\"ALPHA\",\"BETA\",\"SNAPSHOT\",\"MILESTONE\",\"RELEASE\"]");
	}
}
