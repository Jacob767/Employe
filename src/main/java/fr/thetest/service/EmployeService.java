package fr.thetest.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fr.thetest.model.Employe;

public interface EmployeService {
	
	public List<Employe> getEmployeByAgeFromJsonFile(Integer age, MultipartFile file);

}
