package net.harrishproj.ems.service;

import net.harrishproj.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long employeeId,EmployeeDto updateEmployee);
      void deteEmployee(Long employeeId);

}//start()



//This method declaration means:
//
//Input: Takes an EmployeeDto object — typically coming from the client or frontend.
//
//Output: Returns an EmployeeDto — typically the saved version (with an auto-generated ID).
//
//This method is meant to:
//
//Accept new employee data,
//
//Save it,
//
//Return the saved employee info.