package com.espark.adarsh.config;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public interface Configurations {

    String getName();

    String getCountry();

    List<String> getWebsite();

    Address getAddress();

    List<Education> getEducations();

    public static class Address {

        @NotEmpty
        String apt;
        @NotEmpty
        String city;

        public String getApt() {
            return apt;
        }

        public void setApt(String apt) {
            this.apt = apt;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public static class Education {

        @NotEmpty
        private String course;

        @NotEmpty
        private String institution;

        @Max(2018)
        @Min(1990)
        private Integer year;

        private List<Subject> subjects = new ArrayList<>();

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getInstitution() {
            return institution;
        }

        public void setInstitution(String institution) {
            this.institution = institution;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public List<Subject> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<Subject> subjects) {
            this.subjects = subjects;
        }
    }

    public static class Subject{

        String name;
        String mark;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }
    }

}
