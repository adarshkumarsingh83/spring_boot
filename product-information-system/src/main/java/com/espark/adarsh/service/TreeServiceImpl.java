package com.espark.adarsh.service;

import com.espark.adarsh.bean.TreeNodeBean;
import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.TreeNode;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.exception.ValidationFailed;
import com.espark.adarsh.util.ApplicationUtil;
import com.espark.adarsh.util.TreeUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;

import static com.espark.adarsh.util.ApplicationUtil.TREE_TYPE_NOT_FOUND;

@Slf4j
@Service
public class TreeServiceImpl implements TreeService {

    private static Map<String, AbstractService> STORE = new HashMap<>();

    @Autowired
    private TreeUtilService treeUtilService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DimensionService dimensionService;

    @PostConstruct
    public void init() {
        STORE.put(ApplicationUtil.CATEGORY, categoryService);
        STORE.put(ApplicationUtil.DIMENSION, dimensionService);
    }

    @Override
    public TreeNodeBean getTree(String treeType, Optional<Integer> depthParameter)
            throws ValidationFailed, ResourceNotFound {
        TreeNodeBean treeNodeBean = this.getRoot(treeType);
        List<TreeNodeBean> child = this.getChildren(treeType, treeNodeBean, depthParameter);
        treeNodeBean = this.treeUtilService.getTree(treeNodeBean, child);
        return treeNodeBean;
    }

    @Override
    public TreeNodeBean getTree(String treeType, Long id, Optional<Integer> depthParameter)
            throws ValidationFailed, ResourceNotFound {
        TreeNodeBean treeNodeBean = this.getById(treeType, id);
        List<TreeNodeBean> child = this.getChildren(treeType, treeNodeBean, depthParameter);
        treeNodeBean = this.treeUtilService.getTree(treeNodeBean, child);
        return treeNodeBean;
    }

    private TreeNodeBean getRoot(String treeType) throws ValidationFailed {
        if (!STORE.containsKey(treeType)) {
            throw new ValidationFailed(TREE_TYPE_NOT_FOUND, new Object[]{treeType});
        }
        AbstractService abstractService = STORE.get(treeType);
        AbstractEntity rootEntity = abstractService.getRoot();
        return new TreeNodeBean<TreeNode>((TreeNode) rootEntity);
    }

    private TreeNodeBean getById(String treeType, Long id) throws ValidationFailed, ResourceNotFound {
        if (!STORE.containsKey(treeType)) {
            throw new ValidationFailed(TREE_TYPE_NOT_FOUND, new Object[]{treeType});
        }
        AbstractService abstractService = STORE.get(treeType);
        AbstractEntity rootEntity = abstractService.getById(id);
        return new TreeNodeBean<TreeNode>((TreeNode) rootEntity);
    }

    private List<TreeNodeBean> getChildren(String treeType, TreeNodeBean treeNodeBean, Optional<Integer> depthParameter)
            throws ValidationFailed, ResourceNotFound {
        if (!STORE.containsKey(treeType)) {
            throw new ValidationFailed(TREE_TYPE_NOT_FOUND, new Object[]{treeType});
        }
        AbstractService abstractService = STORE.get(treeType);

        List<TreeNodeBean> child = new LinkedList();
        if (depthParameter.isPresent()) {
            if (depthParameter.get() == -1) {
                List<AbstractEntity> entityList = abstractService.getAllLevelChildren(treeNodeBean.getId());
                for (AbstractEntity abstractEntity : entityList) {
                    child.add(new TreeNodeBean<TreeNode>((TreeNode) abstractEntity));
                }
            } else {
                child = this.loadNextLevelChildren(abstractService, treeNodeBean.getId(), child, depthParameter.get() - 1);
            }
        }
        return child;
    }

    private List<TreeNodeBean> loadNextLevelChildren(AbstractService abstractService,
                                                     Long treeNodeBeanId, List<TreeNodeBean> child, Integer depth) {
        if (depth >= 0) {
            depth = depth - 1;
            List<AbstractEntity> nextLevelChildList = abstractService.getNextLevelChildren(treeNodeBeanId);
            if (nextLevelChildList != null && !nextLevelChildList.isEmpty()) {
                for (AbstractEntity abstractEntity : nextLevelChildList) {
                    if (abstractEntity != null) {
                        child.add(new TreeNodeBean<TreeNode>((TreeNode) abstractEntity));
                    }
                }
            }
        }
        return child;
    }

}
