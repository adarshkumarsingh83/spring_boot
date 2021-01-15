package com.espark.adarsh.producer.consumer.bean;

public class Employee {

    public Integer id;

    public String fname;

    public String lname;

    public Double salary;

    public String gender;

    public Employee() {
    }

    public Employee(Integer id, String fname, String lname) {
        super();
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
        this.gender = gender;
    }

    public Employee(Integer id, String fname, String lname, Double salary, String gender) {
        super();
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", salary=" + salary
                + ", gender=" + gender + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fname == null) ? 0 : fname.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + id;
        result = prime * result + ((lname == null) ? 0 : lname.hashCode());
        result = prime * result + ((salary == null) ? 0 : salary.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (fname == null) {
            if (other.fname != null)
                return false;
        } else if (!fname.equals(other.fname))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (id != other.id)
            return false;
        if (lname == null) {
            if (other.lname != null)
                return false;
        } else if (!lname.equals(other.lname))
            return false;
        if (salary == null) {
            if (other.salary != null)
                return false;
        } else if (!salary.equals(other.salary))
            return false;
        return true;
    }


}