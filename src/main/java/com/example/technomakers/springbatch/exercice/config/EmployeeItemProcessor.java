package com.example.technomakers.springbatch.exercice.config;

import com.example.technomakers.springbatch.exercice.model.Employee;
import org.springframework.batch.item.ItemProcessor;

import java.util.Objects;

public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(Employee employee) throws Exception {

        employee.setCity(capitalize(employee.getCity()));

        employee.setGender(employee.getGender().equalsIgnoreCase("male") ? "M" : "F");

        employee.setEverBenched(employee.getEverBenched().equalsIgnoreCase("yes") ? "true" : "false");

        employee.setLeaveOrNot(Objects.equals(employee.getLeaveOrNot(), "1") ? "true" : "false");

        System.err.println("Processing employee: " + employee);
        return employee;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
