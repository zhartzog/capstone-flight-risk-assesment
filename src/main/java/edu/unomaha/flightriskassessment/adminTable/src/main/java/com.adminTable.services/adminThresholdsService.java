package com.adminTable.services;
import java.util.ArrayList;
import java.util.List;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service; 
import com.adminTable.model.*;
import com.adminTable.repository.adminThresholdsRepository;
//defining the business logic
@Service
public class adminThresholdsService
{
@Autowired
adminThresholdsRepository adminThresholds_Repository;
//get all thresholds recorded using CrudRepository findAll()
public List<adminThresholds> getAllThresholds()
{
    List<adminThresholds> adminThresholds = new arrayList<adminThresholds>();
    adminThresholds.findAll().forEach(adminThresholds1 -> adminThresholds.add(adminThresholds1));
    return adminThresholds;
}
//getting a specific record by using the method findById()
public adminThresholds getThresholdsById(int id)
{
    return adminThresholds_Repository.findById(id).get();

}
//saving a specific record by using the method save() of CrudRepository
public void saveOrUpdate(adminThresholds adminThresholds)
{
    adminThresholds_Repository.save(adminThresholds);
}
//deleting a specific record by using the method deleteById()
public void delete(int id)
{
    adminThresholds_Repository.deleteById(id);
}
//updating a threshold
public void update(adminThresholds adminThresholds, int adminThresholdsId)
{
    adminThresholds_Repository.save(adminThresholds);
}
}



