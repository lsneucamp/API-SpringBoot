package co.lsnbox.logistica.service.interfaces;

import co.lsnbox.logistica.domain.Edge;
import co.lsnbox.logistica.domain.Node;
import java.util.List;

public interface IEdgeService {

    Edge findOne(String id);

    List<Edge> findAll();

    List<Edge> findByNodeA(Node node);
    
    /**
     * 
     * @param sourceNode
     * @param destNode
     * @return List of all possible paths found from sourceNode to destNode
     */
    List<List<Edge>> walker(Node sourceNode,Node destNode);

}
