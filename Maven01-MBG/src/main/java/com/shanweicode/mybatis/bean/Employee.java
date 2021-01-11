package com.shanweicode.mybatis.bean;

/**
 * @Package com.shanweicode.mybatis.bean
 * @Author shanweih
 * @Date 2020/12/21 18:20
 * @TOOL IntelliJ IDEA
 */
public class Employee {
    private Integer empId;
    private String empName;
    private Double empSalary;
    private Integer empAge;
    private String empGender;
    private String empAddress;

    public Employee() {
    }

    public Employee(Integer empId, String empName, Double empSalary, Integer empAge, String empGender, String empAddress) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
        this.empGender = empGender;
        this.empAddress = empAddress;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empSalary=" + empSalary +
                ", empAge=" + empAge +
                ", empGender='" + empGender + '\'' +
                ", empAddress='" + empAddress + '\'' +
                '}';
    }
}
