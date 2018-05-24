package com.xidian.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    @Query("from Employee e where e.id=:id")
    Employee findEmployee(@Param("id") int id);
}
