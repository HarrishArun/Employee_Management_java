//Why Map Between Entity and DTO?
//You don’t want to expose internal database structure directly to clients (security, encapsulation).
//
//DTO can have only the required fields clients need, ignoring extra internal fields.
//
//DTOs make it easier to control and validate input/output data.
//
//Entities often have relationships, lazy loading, or database-specific details you don’t want to share directly.


package net.harrishproj.ems.mapper;

import net.harrishproj.ems.entity.Employee;
import net.harrishproj.ems.dto.EmployeeDto;

public class EmployeeMapper {

    public  static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );

    }
    //You get an Employee entity from the database.
    //
    //This method creates a new EmployeeDto and copies relevant fields.
    //
    //The DTO is then sent back to the frontend, e.g., in an API response

    //This method takes an Employee object as input and returns an EmployeeDto.
    //
    //It copies the values (id, firstName, lastName, email) from the Employee object to a new EmployeeDto object.
    //
    //Use case: When you're sending employee data from your backend to the frontend, you often use a DTO.
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }


}
//You get an EmployeeDto from the frontend, e.g., from a form submission or API request.
//
//This method creates a new Employee entity and copies the DTO fields into it.
//
//This entity can then be saved to the database.


//This method takes an EmployeeDto object and returns an Employee.
//
//It copies values from the DTO back to the entity object.
//
//Use case: When receiving data from the frontend (like in a form submission), you convert the DTO into an entity before saving to the database.