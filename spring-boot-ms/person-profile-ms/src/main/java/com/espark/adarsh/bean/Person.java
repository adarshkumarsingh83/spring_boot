package com.espark.adarsh.bean;

public class Person {
    String personName;
    String personEmail;
    String personPhone;

    public Person() {
    }

    public Person(String personName, String personEmail, String personPhone) {
        this.personName = personName;
        this.personEmail = personEmail;
        this.personPhone = personPhone;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }
}
