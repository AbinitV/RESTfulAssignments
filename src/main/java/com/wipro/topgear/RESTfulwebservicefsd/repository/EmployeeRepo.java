package com.wipro.topgear.RESTfulwebservicefsd.repository;

import com.wipro.topgear.RESTfulwebservicefsd.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {

}
