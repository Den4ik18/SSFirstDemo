package com.model;


import com.dateadapter.MyAdapter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.dateadapter.LocalDateDeserializer;
import com.dateadapter.LocalDateSerializer;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;



@XmlRootElement
public class Employee {
    @NotNull(message = "id must be not null")
    @PositiveOrZero(message = "id must be positive or zero")
    private Long id;

    @NotNull(message = "name must be not null")
    @NotBlank(message = "name can't be blank")
    @Pattern(regexp = "^[A-Z]?[a-z]*(?:-[A-Z][a-z]*)?$")
    private String name;

    @NotNull(message = "phone number must be not null")
    @NotBlank(message = "phone number must be not blank")
    @Pattern(regexp = "^\\+?\\d{12}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
    private String phoneNumber;

    //    @XmlElement(name = "lastName")
    @NotNull(message = "last name must be not null")
    @NotBlank(message = "last name can't be blank")
    private String lastName;

    @NotNull(message = "address must be not null")
    private Address address;

    @NotNull(message = "sex must be not null")
    @NotBlank(message = "sex can't be blank")
    private String sex;

    @NotNull(message = "email must be not null")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+(com|ua|ru|org)$")
    private String email;

    @NotNull(message = "date of birth must be not null")
    @Past(message = "date of birth must be in past")
    @Pattern(regexp = "\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])*")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;


    @NotNull(message = "job list must be not null")
    private List<Job> jobs = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, String lastName, String phoneNumber, Address address, String sex, String email, LocalDate dateOfBirth, List<Job> jobs) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sex = sex;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.jobs = jobs;
    }

    public Employee(String name, String lastName, String phoneNumber, Address address, String sex, String email, List<Job> jobs) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.sex = sex;
        this.email = email;
        this.jobs = jobs;
    }

    public Employee(Long id, String name, String lastName, String phoneNumber, Address address, String sex, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.address = address;
        this.sex = sex;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee(Long id, String name, String lastName, String phoneNumber, Address address, String sex, String email, LocalDate dateOfBirth, List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
        this.address = address;
        this.sex = sex;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.jobs = jobs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(phoneNumber, employee.phoneNumber) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(address, employee.address) &&
                Objects.equals(sex, employee.sex) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(jobs, employee.jobs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, lastName, address, sex, email, dateOfBirth, jobs);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @XmlJavaTypeAdapter(value = MyAdapter.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", jobs=" + jobs +
                '}';
    }

}
