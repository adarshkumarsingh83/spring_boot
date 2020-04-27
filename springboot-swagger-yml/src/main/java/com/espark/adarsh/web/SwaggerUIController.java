package com.espark.adarsh.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/swagger")
public class SwaggerUIController {

    @GetMapping("/{name}/{version}")
    public String homePage(@PathVariable("name") String name, @PathVariable("version") String version, Model model) {
        model.addAttribute("filePath", "/swagger/" + name + '/' + version + "/swagger.yaml");
        return "redoc";
    }
}