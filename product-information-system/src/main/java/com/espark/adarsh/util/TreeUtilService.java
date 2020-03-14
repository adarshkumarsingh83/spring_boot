package com.espark.adarsh.util;

import com.espark.adarsh.bean.TreeNodeBean;
import com.espark.adarsh.entity.AbstractEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TreeUtilService {

    public TreeNodeBean getTree(TreeNodeBean rootNode, List<TreeNodeBean> childTreeNodes) {
        log.debug("label=tree-util getTree() rootNode={} childTreeNodes={}", rootNode, childTreeNodes);
        return this.getTreeInternal(rootNode, childTreeNodes);
    }

    private TreeNodeBean getTreeInternal(TreeNodeBean rootNode, List<TreeNodeBean> childTreeNodes) {
        log.debug("label=tree-util ::-> getTreeInternal() rootNode={} childTreeNodes={}", rootNode, childTreeNodes);
        TreeNodeBean treeNodeBean1 = rootNode;
        if (childTreeNodes != null && !childTreeNodes.isEmpty()) {
            Map<Long, List<TreeNodeBean>> groupedChild =
                    childTreeNodes.stream()
                            .filter(treeNodeBean -> treeNodeBean != null)
                            .collect(Collectors.groupingBy(treeNodeBean -> treeNodeBean.getParentId()));
            List<TreeNodeBean> child = groupedChild.get(rootNode.getId());
            groupedChild.remove(rootNode.getId());
            treeNodeBean1 = getTreeHierarchy(rootNode, child, groupedChild);
        }
        log.debug("label=tree-util <-:: getTreeInternal() treeNodeBean1={} ", treeNodeBean1);
        return treeNodeBean1;
    }

    private TreeNodeBean getTreeHierarchy(TreeNodeBean treeNodeBean, List<TreeNodeBean> directChild
            , Map<Long, List<TreeNodeBean>> groupedTotalChild) {
        log.debug("label=tree-util ::-> getTreeHierarchy() treeNodeBean={} directChild={} groupedTotalChild={}"
                , treeNodeBean, directChild, groupedTotalChild);
        if (directChild != null && !directChild.isEmpty()) {
            for (TreeNodeBean nodeBean : directChild) {
                TreeNodeBean childTreeNodeBean = new TreeNodeBean(nodeBean.getTreeNode());
                if (treeNodeBean != null) {
                    treeNodeBean.setChildren(childTreeNodeBean);
                } else {
                    treeNodeBean = childTreeNodeBean;
                }
                List<TreeNodeBean> child = groupedTotalChild.get(nodeBean.getId());
                groupedTotalChild.remove(nodeBean.getId());
                this.getTreeHierarchy(childTreeNodeBean, child, groupedTotalChild);
            }
        }
        treeNodeBean = this.sortTreeNodeBean(treeNodeBean);
        log.debug("label=tree-util <-:: getTreeHierarchy() treeNodeBean={} ", treeNodeBean);
        return treeNodeBean;
    }

    private TreeNodeBean sortTreeNodeBean(TreeNodeBean treeNodeBean) {
        log.debug("label=tree-util ::-> sortTreeNodeBean() treeNodeBean={} ", treeNodeBean);
        List<TreeNodeBean> children = treeNodeBean.getChildren();
        Map<AbstractEntity.Type, List<TreeNodeBean>> groupedChild
                = children
                .stream()
                .collect(Collectors.groupingBy((TreeNodeBean treeNode) -> treeNode.getType()));
        children = groupedChild
                .entrySet()
                .stream()
                .map(element -> element.getValue()).
                        flatMap(l -> l.stream()).
                        collect(Collectors.toList());
        treeNodeBean.getChildren().clear();
        treeNodeBean.setChildren(children);
        log.debug("label=tree-util <-:: sortTreeNodeBean() treeNodeBean={} ", treeNodeBean);
        return treeNodeBean;
    }
}
