package com.espark.adarsh.util;

import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.AbstractEntity.Type;
import com.espark.adarsh.entity.Category;
import com.espark.adarsh.entity.Dimension;
import com.espark.adarsh.entity.Product;
import com.espark.adarsh.exception.ApplicationException;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.service.AbstractService;
import com.espark.adarsh.service.CategoryService;
import com.espark.adarsh.service.DimensionService;
import com.espark.adarsh.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.espark.adarsh.util.ApplicationUtil.DATA_NOT_FOUND;

@Slf4j
@Service
public class TreeClimberUtil {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DimensionService dimensionService;

    private static Map<Type, AbstractService> STORE = null;

    @PostConstruct
    public void init() {
        STORE = new HashMap<Type, AbstractService>() {
            {
                put(Type.category, categoryService);
                put(Type.dimension, dimensionService);
            }
        };
        log.debug("label=tree-climber init() store={}", STORE);
    }

    public TreeClimber getTreeClimber() {
        return new TreeClimber(new HashMap<>());
    }

    public List<AbstractEntity> getAllData(TreeClimber treeClimber) {
        List<AbstractEntity> response = treeClimber.getParentLadder();
        log.debug("label=tree-climber <-:: getAllData() response={}", response);
        return response;
    }

    public class TreeClimber {

        boolean selfClimb = false;
        boolean parentLadderFlag = false;
        private AbstractEntity node;
        private Predicate<AbstractEntity> predicate;
        Map<Long, AbstractEntity> parentLadder = null;

        public TreeClimber(Map<Long, AbstractEntity> parentLadder) {
            this.parentLadder = parentLadder;
        }

        public TreeClimber withNode(AbstractEntity node, boolean parentLadderFlag) {
            this.node = node;
            this.parentLadderFlag = parentLadderFlag;
            return this;
        }

        public TreeClimber withPredicate(Predicate predicate) {
            this.predicate = predicate;
            return this;
        }

        public TreeClimber withSelfClimb(boolean selfClimb) {
            this.selfClimb = selfClimb;
            return this;
        }

        public AbstractEntity climb() {
            log.debug("label=tree-climber ::-> climb() node={}", node);
            try {
                Assert.notNull(predicate, "predicate is required!");
                return climbInternally(selfClimb ? node : this.getParent(node));
            } catch (Exception rethrow) {
                throw new ApplicationException(rethrow);
            }
        }

        private AbstractEntity climbInternally(AbstractEntity abstractEntity) throws ResourceNotFound {
            log.debug("label=tree-climber ::-> climbInternally() abstractEntity={}", abstractEntity);
            try {
                if (parentLadderFlag) {
                    if (!parentLadder.containsKey(abstractEntity.getId())) {
                        parentLadder.put(abstractEntity.getId(), abstractEntity);
                    }
                    if (predicate.test(abstractEntity)) {
                        return abstractEntity;
                    }

                    if (parentLadder.containsKey(abstractEntity.getParent())) {
                        abstractEntity = parentLadder.get(abstractEntity.getParent());
                    } else {
                        abstractEntity = this.getParent(abstractEntity);
                    }
                    return climbInternally(abstractEntity);
                } else {
                    return climbInternally(this.getParent(abstractEntity));
                }
            } catch (Exception rethrow) {
                throw new ResourceNotFound(DATA_NOT_FOUND, new Object[]{abstractEntity});
            }
        }

        public List<AbstractEntity> getParentLadder() {
            log.debug("label=tree-climber ::-> getParentLadder() parentLadder={}", parentLadder);
            Collection collection = (parentLadder != null && parentLadder.values() != null) ? parentLadder.values() : null;
            return (collection != null) ? new LinkedList<AbstractEntity>(collection) : new LinkedList<AbstractEntity>();
        }

        private AbstractEntity getParent(AbstractEntity abstractEntity) throws ResourceNotFound {
            log.debug("label=tree-climber ::-> getParent() abstractEntity={}", abstractEntity);
            AbstractEntity parent = null;
            Type type = abstractEntity.getType();
            if (STORE.containsKey(type)) {
                AbstractService abstractService = STORE.get(type);
                parent = abstractService.getById(abstractEntity.getParent());
            }
            log.debug("label=tree-climber <-:: getParent() parent={}", parent);
            return parent;
        }
    }
}
