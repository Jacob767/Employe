package fr.thetest.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.thetest.model.Employe;
import fr.thetest.repository.EmployeRepository;

@Service
public class EmployeServiceImpl implements EmployeService{

	@Autowired
	EmployeRepository employeRepository;
	
	@Autowired
	MongoOperations ops;

	@Override
	public List<Employe> getEmployeByAgeFromJsonFile(Integer age, MultipartFile file) {
		ObjectMapper objectMapper = new ObjectMapper();
		Long jobId = new Timestamp(System.currentTimeMillis()).getTime();
		List<Employe> res=null;
		try {
			List<Employe> listEmploye = objectMapper.readValue(file.getInputStream(), new TypeReference<List<Employe>>(){});
			List<Employe> list = listEmploye.stream().distinct().collect(Collectors.toList());
			list.forEach(e->e.setJobId(jobId));
			ops.insertAll(list);
			
			res = employeRepository.findByAgeAndJobId(age,jobId);
			employeRepository.findByJobId(jobId).forEach(e->ops.remove(e));
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
