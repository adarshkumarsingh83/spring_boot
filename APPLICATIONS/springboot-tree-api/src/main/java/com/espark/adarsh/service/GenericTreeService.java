package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.bean.GenericBean;
import com.espark.adarsh.bean.TreeNode;
import com.espark.adarsh.entity.Department;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.entity.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class GenericTreeService implements TreeService {

    @Autowired
    DepartmentEmployeeService departmentEmployeeService;


    @Override
    public TreeNode<Node> getRoot(String type) {
        final TreeNode<Node> treeNode = new TreeNode<>();
        if (type.equalsIgnoreCase(ALL_TREE)) {
            GenericBean genericBean = new GenericBean(departmentEmployeeService.getDepartment(101L));
            treeNode.setData(genericBean);
        } else {
            throw new RuntimeException("Invalid Type ");
        }
        return treeNode;
    }

    @Override
    public TreeNode<Node> getRootWithChild(String type, int depth) {
        return this.buildTree(this.getRoot(type), depth - 1);
    }

    @Override
    public List<TreeNode> getChildren(String type, int depth) {
        return this.buildTree(this.getRoot(type), depth - 1).getChildren();
    }


    private TreeNode<Node> buildTree(TreeNode<Node> root, int depth) {
        if (depth > 0) {
            if (root != null && root.getData() != null) {
                GenericBean genericBean = (GenericBean) root.getData();
                List<Employee> employees = this.departmentEmployeeService.findManagerBytDeptId(genericBean.getDeptId(), "-mgr");
                if (employees != null && employees.size() > 0) {
                    employees.stream()
                            .filter(employee -> employee != null)
                            .map(employee -> new EmployeeBean(employee))
                            .forEach(employeeBean -> {
                                genericBean.setEmployees(this.buildEmployeeTree(employeeBean, 100));
                            });

                }
                List<Department> departments = this.departmentEmployeeService.getChildDepartment(genericBean.getDeptId());
                if (departments != null && departments.size() > 0) {
                    for (Department department : departments) {
                        TreeNode dept = buildTree(new TreeNode(new GenericBean(department)), depth - 1);
                        root.setChildren(dept);
                    }
                }
            }
        }
        return root;
    }

    private EmployeeBean buildEmployeeTree(EmployeeBean root, int depth) {
        if (depth > 0) {
            if (root != null) {
                List<Employee> employees = departmentEmployeeService.getEmployeeByManagerId(root.getId());
                if (employees != null && employees.size() > 0) {
                    for (Employee emp : employees) {
                        EmployeeBean node = buildEmployeeTree(new EmployeeBean(emp), depth - 1);
                        root.setChildren(node);
                    }
                }
            }
        }
        return root;
    }
}
