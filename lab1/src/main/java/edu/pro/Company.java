package edu.pro;/*
  @author tanus
  @project lab1
  @class Company
  @version 1.0.0
  @since 18.03.2026 - 17.29
*/

public class Company {

    private Company parent;
    private long employeesCount;

    public Company(Company parent, long employeesCount) {
        this.parent = parent;
        this.employeesCount = employeesCount;
    }

    public Company getParent() {
        return parent;
    }

    public long getEmployeesCount() {
        return employeesCount;
    }
}