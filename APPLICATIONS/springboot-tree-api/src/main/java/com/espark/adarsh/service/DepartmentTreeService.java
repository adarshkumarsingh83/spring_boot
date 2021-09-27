package com.espark.adarsh.service;

import com.espark.adarsh.bean.DepartmentBean;
import com.espark.adarsh.bean.TreeNode;
import com.espark.adarsh.entity.Department;
import com.espark.adarsh.entity.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentTreeService implements TreeService {

    @Autowired
    DepartmentEmployeeService departmentEmployeeService;


    @Override
    public TreeNode<Node> getRoot(String type) {
        TreeNode<Node> treeNode = null;
        if (type.equalsIgnoreCase(DEPT_TREE)) {
            treeNode = new TreeNode<>();
            treeNode.setData(new DepartmentBean(departmentEmployeeService.getDepartment(101L)));
        } else {
            throw new RuntimeException("Invalid Tree Type ");
        }
        return treeNode;
    }

    @Override
    public TreeNode<Node> getRootWithChild(String type, int depth) {
        return buildTree(getRoot(type), depth - 1);
    }

    @Override
    public List<TreeNode> getChildren(String type, int depth) {
        return buildTree(getRoot(type), depth - 1).getChildren();
    }

    private TreeNode<Node> buildTree(TreeNode<Node> root, int depth) {
        if (depth > 0) {
            if (root != null && root.getData() != null) {
                DepartmentBean department = (DepartmentBean) root.getData();
                List<Department> departments = departmentEmployeeService.getChildDepartment(department.getDeptId());
                if (department != null && departments.size() > 0) {
                    for (Department dept : departments) {
                        TreeNode<Node> node = buildTree(new TreeNode<>(new DepartmentBean(dept)), depth - 1);
                        root.setChildren(node);
                    }
                }
            }
        }
        return root;
    }
}
