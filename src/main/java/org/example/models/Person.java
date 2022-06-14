package org.example.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name has incorrect size!")
    private String fullName;

    @Min(value = 1900, message = "Birth year should be greater than 1900")
    @Max(value = 2015, message = "Birth year should be less than 2015")
    private int birthYear;

//    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "Email should be valid")
//    private String email;
//
//    @Pattern(regexp = "[A-ZА-Я][a-zа-я]+, [A-ZА-Я][a-zа-я]+, \\d{6}", message = "Your address should be in this format: Country, City Postal Code (6 digits)")
//    private String address;

    public Person() {
    }

    public Person(int id, String fullName, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
//        this.email = email;
//        this.address = adress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getBirthYear() {
        return birthYear;
    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getAddress() {
//        return address;
//    }
}
