package com.assignment.employwise.repositories;

import com.assignment.employwise.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface EmployeeRepo extends MongoRepository<Employee, UUID> {
}
