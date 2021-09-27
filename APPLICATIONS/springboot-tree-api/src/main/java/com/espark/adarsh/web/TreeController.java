package com.espark.adarsh.web;

import com.espark.adarsh.bean.TreeNode;
import com.espark.adarsh.service.DepartmentTreeService;
import com.espark.adarsh.service.EmployeeTreeService;
import com.espark.adarsh.service.GenericTreeService;
import com.espark.adarsh.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
public class TreeController {

    private static final Map<String, TreeService> registeredTreeServices = new HashMap();

    @Autowired
    EmployeeTreeService employeeTreeService;

    @Autowired
    DepartmentTreeService departmentTreeService;

    @Autowired
    GenericTreeService genericTreeService;

    @PostConstruct
    void init() {
        registeredTreeServices.put(TreeService.EMP_TREE, employeeTreeService);
        registeredTreeServices.put(TreeService.DEPT_TREE, departmentTreeService);
        registeredTreeServices.put(TreeService.ALL_TREE, genericTreeService);
    }

    @GetMapping("/tree/{treeType}/{depth}")
    public TreeNode getTree(@PathVariable("treeType") String treeType, @PathVariable("depth") Optional<Integer> depth) {
        return registeredTreeServices.get(treeType).getRootWithChild(treeType, depth.orElseGet(() -> {
            return 10;
        }));
    }


}
