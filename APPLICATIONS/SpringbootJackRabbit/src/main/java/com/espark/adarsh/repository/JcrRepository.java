package com.espark.adarsh.repository;

import javax.jcr.Node;
import java.util.Map;

public interface JcrRepository {

       public Node addNode(String parent, String node , Map<String,Object> properties)throws Exception;

       public Node getNode(String node)throws Exception;

       public Node deleteNode(String node)throws Exception;

       public Node updateNode(String node, Map<String, Object> properties)throws Exception;

}
