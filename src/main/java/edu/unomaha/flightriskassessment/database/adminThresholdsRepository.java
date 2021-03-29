package edu.unomaha.flightriskassessment.database;
import org.springframework.data.repository.CrudRepository;
import edu.unomaha.flightriskassessment.models.adminThresholds;
//repository that extends CrudRepository
public interface adminThresholdsRepository extends CrudRepository<adminThresholds, Integer> {}