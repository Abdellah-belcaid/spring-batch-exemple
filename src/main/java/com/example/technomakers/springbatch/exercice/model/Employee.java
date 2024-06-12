package com.example.technomakers.springbatch.exercice.model;


import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String education;
    private int joiningYear;
    private String city;
    private int paymentTier;
    private int age;
    private String gender;
    private String everBenched;
    private int experienceInCurrentDomain;
    private String leaveOrNot;


}
