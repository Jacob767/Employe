package fr.thetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.multipart.MultipartFile;

import fr.thetest.model.Employe;
import fr.thetest.service.EmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeApiApplicationTests {


	@Autowired
	private EmployeService employeService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testService() throws Exception {
		Path path = Paths.get("data.json");
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MultipartFile file = new MockMultipartFile("file", "", null, content);

		List<Employe> res = employeService.getEmployeByAgeFromJsonFile(20, file);

		assertThat(1).isEqualTo(res.size());

	}

	@Test
	public void testController() throws Exception {
		Path path = Paths.get("data.json");
		byte[] content = null;
		try {
		    content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		MockMultipartFile file = new MockMultipartFile("file", "",  null, content);
		
		mockMvc.perform(fileUpload("/api/1.0/employe/upload/20").file(file))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content()
				.json("[{\"matricule\":\"1340\",\"nom\":\"Alain\",\"prenom\":\"michelle\",\"age\":20}]"));

		
		
	}

}
