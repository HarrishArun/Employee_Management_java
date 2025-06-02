package net.harrishproj.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//Needed by frameworks like JPA/Hibernate for object creation through reflection.
//
//Useful when you want to create an object and set values later using setters.
@AllArgsConstructor
//Convenient when you want to quickly create an object with all fields populated.
//Generates a constructor with one parameter for each field in the class.

//When you use both @NoArgsConstructor and @AllArgsConstructor together, it provides flexibility for different use cases in your application.
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;


}
