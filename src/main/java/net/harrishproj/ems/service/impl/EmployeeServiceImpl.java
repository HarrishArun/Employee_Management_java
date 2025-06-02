package net.harrishproj.ems.service.impl;

import lombok.AllArgsConstructor;
import net.harrishproj.ems.entity.Employee;
import net.harrishproj.ems.dto.EmployeeDto;
import net.harrishproj.ems.exceptions.ResourceNotFoundException;
import net.harrishproj.ems.mapper.EmployeeMapper;
import net.harrishproj.ems.repository.EmployeeRepository;
import net.harrishproj.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//petrol engine implements i engine
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

     //private iengine eng; it is not initalized
    // ✅ Regular constructor for dependency injection (no Lombok)


    //Spring (the mechanic) creates and injects the engine (EmployeeRepository) into the car (EmployeeServiceImpl) when the app starts.
    //
    //You can easily change the repository (engine) later, like for testing, or use a mock.
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override //start()
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
      //  DTO-> entity
        //Why? You receive data from the client in the form of a DTO (Data Transfer Object).
        //
        //But the database doesn’t understand DTOs — it only saves entities (like Employee).
        //
        //So, you map the EmployeeDto to an Employee object using the EmployeeMapper.
        Employee employee= EmployeeMapper .mapToEmployee(employeeDto);





//
        //Now that you have a proper Employee entity object, you save it in the database using employeeRepository.
        //
        //This repository is a Spring Data JPA interface that connects to the database.
        //
        //save() method inserts the employee into the database, and returns the saved entity (possibly with an auto-generated ID).ave to db
        //save to db
      Employee savedEmployee=  employeeRepository.save(employee);







      //entity-> DTO
        //After saving, you don’t want to return the entity directly to the frontend or API response.
        //
        //So you map the entity back to a DTO using the same mapper class.
        //
        //This DTO is returned to the user or controller, hiding internal DB details.
      return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override//we need exception bz user might not exist
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee=employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee not exist with given ID :"+employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();

        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
      Employee employee=  employeeRepository.findById(employeeId).orElseThrow(()
                ->new ResourceNotFoundException("Employee not exist with given ID :"+employeeId));
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee updatedEmployeeObj=  employeeRepository.save(employee);//update+save


        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);

    }

    @Override
    public void deteEmployee(Long employeeId) {
        Employee employee=  employeeRepository.findById(employeeId).orElseThrow(()
                ->new ResourceNotFoundException("Employee not exist with given ID :"+employeeId));

        employeeRepository.deleteById(employeeId);
    }


}

