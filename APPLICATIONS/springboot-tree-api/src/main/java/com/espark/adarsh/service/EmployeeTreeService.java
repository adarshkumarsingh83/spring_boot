package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.bean.TreeNode;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.entity.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeTreeService implements TreeService {

    @Autowired
    DepartmentEmployeeService departmentEmployeeService;


    @Override
    public TreeNode<Node> getRoot(String type) {
        TreeNode<Node> treeNode = null;
        if (type.equalsIgnoreCase(EMP_TREE)) {
            treeNode = new TreeNode<>();
            treeNode.setData(new EmployeeBean(departmentEmployeeService.getEmployeeByManagerId(0L).get(0)));
        } else {
            throw new RuntimeException("Invalid Tree Type ");
        }
        return treeNode;
    }

    @Override
    public TreeNode<Node> getRootWithChild(String type, int depth) {
        return this.buildTree(this.getRoot(type), depth-1);
    }

    @Override
    public List<TreeNode> getChildren(String type, int depth) {
        return this.buildTree(this.getRoot(type), depth-1).getChildren();
    }


    private TreeNode<Node> buildTree(TreeNode<Node> root, int depth) {
        if (depth > 0) {
            if (root != null && root.getData() != null) {
                EmployeeBean employee = (EmployeeBean) root.getData();
                List<Employee> employees = departmentEmployeeService.getEmployeeByManagerId(employee.getId());
                if (employees != null && employees.size() > 0) {
                    for (Employee emp : employees) {
                        TreeNode<Node> node = buildTree(new TreeNode<>(new EmployeeBean(emp)), depth - 1);
                        root.setChildren(node);
                    }
                }
            }
        }
        return root;
    }
}
