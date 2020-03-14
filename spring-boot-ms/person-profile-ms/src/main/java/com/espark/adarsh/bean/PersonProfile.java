package com.espark.adarsh.bean;

public class PersonProfile {

    private String profileId;
    private Person person;
    private Address address;

    public PersonProfile() {
    }

    public PersonProfile(String profileId, Person person, Address address) {
        this.profileId = profileId;
        this.person = person;
        this.address = address;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
