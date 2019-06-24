package com.espark.adarsh.controller;

import com.espark.adarsh.config.Configurations;
import com.espark.adarsh.config.PropertiesConfiguration;
import com.espark.adarsh.config.YamlConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ConfigurationController {

    @Autowired
    private YamlConfiguration yamlConfiguration;

    @Autowired
    private PropertiesConfiguration propertiesConfiguration;

    @RequestMapping(value = "/values/{type}", method = RequestMethod.GET)
    public ConfigurationBean getConfigurations(@PathVariable("type") String type) {
        ConfigurationBean configurationBean = null;
        if (type.equalsIgnoreCase("prop")) {
            configurationBean = new ConfigurationBean(propertiesConfiguration);
        } else if (type.equalsIgnoreCase("yml")) {
            configurationBean = new ConfigurationBean(yamlConfiguration);
        } else {
            throw new RuntimeException("Invalid Type ");
        }
        return configurationBean;
    }

    class ConfigurationBean {

        private String name;
        private String country;
        private List<String> website = new ArrayList<>();
        private Configurations.Address address;
        private List<Configurations.Education> educations = new ArrayList<>();

        public ConfigurationBean(Configurations configurations) {
            this.name = configurations.getName();
            this.country = configurations.getCountry();
            this.website = configurations.getWebsite();
            this.address = configurations.getAddress();
            this.educations = configurations.getEducations();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public List<String> getWebsite() {
            return website;
        }

        public void setWebsite(List<String> website) {
            this.website = website;
        }

        public Configurations.Address getAddress() {
            return address;
        }

        public void setAddress(Configurations.Address address) {
            this.address = address;
        }

        public List<Configurations.Education> getEducations() {
            return educations;
        }

        public void setEducations(List<Configurations.Education> educations) {
            this.educations = educations;
        }
    }
}
