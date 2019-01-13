package fr.thetest.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import fr.thetest.model.Employe;

public interface EmployeRepository extends  MongoRepository<Employe, ObjectId>  {

	List<Employe> findByAgeAndJobId(Integer age, Long jobId);

	List<Employe> findByJobId(Long jobId);

}
