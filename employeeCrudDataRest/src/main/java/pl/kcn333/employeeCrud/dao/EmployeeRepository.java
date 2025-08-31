package pl.kcn333.employeeCrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.kcn333.employeeCrud.entity.Employee;

@RepositoryRestResource(path="employees")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
