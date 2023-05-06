package com.homework22.homework22;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int department;
    private double salary;
    private static int nextId = 0;
    private int id;

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public Employee(String firstName, String lastName, int department, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
        nextId++;
        this.id = nextId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Имя: " + firstName + " Фамилия: " + lastName +  "Id: " + id +
                " Отдел: " + department + " Зарплата: " + salary;
    }
}
