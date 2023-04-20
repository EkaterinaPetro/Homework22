package com.homework22.homework22;

public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException() {
        super("Такой сотрудник уже есть");
    }
}
