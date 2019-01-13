package fr.thetest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.thetest.model.Employe;
import fr.thetest.service.EmployeService;

@RestController
@RequestMapping("/api/1.0/employe")
public class EmployeController {

	@Autowired
	private EmployeService employeSerice;
	 
//	@CrossOrigin(origins = "http://localhost:4200")   //Si on demarre le front avec le client angular
	@CrossOrigin(origins = "null")	//Si on utilise la page html buildé
	@PostMapping("/upload/{age}")
	public ResponseEntity<List<Employe>> getEmployeByAgeFromJsonFile(@PathVariable Integer age,
			@RequestParam("file") MultipartFile file) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(employeSerice.getEmployeByAgeFromJsonFile(age, file));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(null);
		}
	}

}
