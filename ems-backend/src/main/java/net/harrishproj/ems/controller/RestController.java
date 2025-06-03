package net.harrishproj.ems.controller;


import net.harrishproj.ems.dto.EmployeeDto;

import net.harrishproj.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Marks the class as a REST Controller in Spring.
//
//Combines @Controller + @ResponseBody.
//
//All methods in this class will return JSON responses by default.
//
//Used for creating RESTful web services.
@org.springframework.web.bind.annotation.RestController

//Sets the base URL path for all methods in this controller.
//
//This means:
//
//If you later add a method mapped to /create, it would respond to /api/employees/create.

@RequestMapping("/api/employees")



public class RestController {

    //This is a dependency (your business logic class).
    //
    //It's declared here so that the controller can use it to perform actions like saving an employee.
    private  EmployeeService employeeService;


    public  RestController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }//constructor injection


    //Frontend sends a POST request to /api/employees with employee JSON data.
    //
    //Spring Boot maps the request to createEmployee(...) method.
    //
    //The EmployeeDto is received via @RequestBody.
    //
    //EmployeeService.createEmployee() is called to handle the business logic and save to DB.
    //
    //The method returns a ResponseEntity with status 201 Created and saved data.


    //Receives an EmployeeDto object (likely from a POST request).
    //

//create
@PostMapping//It should handle POST requests.


public ResponseEntity<EmployeeDto> createEmployee( @RequestBody EmployeeDto employeeDto){
//json->employee dto
        //@RequestBody tells Spring:
    //
    //"Hey Spring, take the JSON data from the request body,
    // convert it into a Java object of type EmployeeDto,
    // and assign it to the parameter employeeDto."

        //   //Calls the service method to save the employee:
        EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);


        //Returns an HTTP response:
    //
    //201 CREATED status code (standard for resource creation).
    //
    //Response body is the saved employee.
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
}

//read
@GetMapping("{id}")
public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
      EmployeeDto employeeDto= employeeService.getEmployeeById(employeeId);
      return ResponseEntity.ok(employeeDto);

}
//read all
@GetMapping    //api/employees
public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
    List<EmployeeDto> employees=employeeService.getAllEmployees();
    return  ResponseEntity.ok(employees);
}
//Update

@PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id")
                                                       Long employeeId, @RequestBody EmployeeDto updatedEmployee){
      EmployeeDto employeeDto=  employeeService.updateEmployee(employeeId,updatedEmployee);
      return ResponseEntity.ok(employeeDto);
}
//delete
@DeleteMapping("{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deteEmployee(employeeId);
        return  ResponseEntity.ok("Employee Deleted Successfully");
}

 }