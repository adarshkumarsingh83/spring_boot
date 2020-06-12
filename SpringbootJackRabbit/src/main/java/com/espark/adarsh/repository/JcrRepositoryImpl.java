package com.espark.adarsh.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import java.util.Map;

@Service("jcrRepository")
public class JcrRepositoryImpl implements JcrRepository {


    @Value("${app.jcr.user.name}")
    private String jcrUserName;

    @Value("${app.jcr.user.pwd}")
    private String jcrUserPwd;

    @Value("${app.jcr.root.name}")
    private String jcrRoot;

    @Autowired
    private Repository repository;

    private Node rootNode;


    @PostConstruct
    public void init() throws Exception {
        final Session session = this.getSession();
        this.rootNode = session.getRootNode();
        this.rootNode = this.rootNode.addNode(jcrRoot);
    }


    @Override
    public Node addNode(String parent, String node, Map<String, Object> properties) throws Exception {
        final Session session = this.getSession();
        Node parentNode = session.getNode(parent);
        Node childNode = null;
        if (parentNode != null) {
            childNode = parentNode.addNode(node);
            for (Map.Entry<String, Object> entry : properties.entrySet()) {
                childNode.setProperty(entry.getKey(), entry.getValue().toString());
            }
            session.save();
        }
        return childNode;
    }

    @Override
    public Node getNode(String node) throws Exception {
        final Session session = this.getSession();
        return session.getNode(node);
    }

    @Override
    public Node deleteNode(String node) throws Exception {
        final Session session = this.getSession();
        Node removeNode = session.getNode(node);
        removeNode.remove();
        return removeNode;
    }

    @Override
    public Node updateNode(String node, Map<String, Object> properties) throws Exception {
        final Session session = this.getSession();
        Node updateNode = session.getNode(node);
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            updateNode.setProperty(entry.getKey(), entry.getValue().toString());
        }
        updateNode.update(node);
        return updateNode;
    }


    public Session getSession() throws Exception {
        return this.repository.login(
                new SimpleCredentials(jcrUserName, jcrUserPwd.toCharArray()));
    }
}
