package co.lsnbox.logistica.service;

import co.lsnbox.logistica.domain.Edge;
import co.lsnbox.logistica.domain.Node;
import co.lsnbox.logistica.exceptions.APIBadRequestException;
import co.lsnbox.logistica.repository.mongo.EdgeRepository;
import co.lsnbox.logistica.service.interfaces.IEdgeService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EdgeServiceImpl implements IEdgeService {

    private static Logger LOG = LoggerFactory.getLogger(EdgeServiceImpl.class);

    @Inject
    EdgeRepository edgeRepository;

    @Override
    public Edge findOne(String id) {
        return edgeRepository.findOne(id);
    }

    @Override
    public List<Edge> findAll() {
        return edgeRepository.findAll();
    }

    @Override
    public List<Edge> findByNodeA(Node node) {
        return edgeRepository.findByNodeA(node);
    }

    @Override
    public List<List<Edge>> walker(Node sourceNode, Node destNode) {
        return walker(sourceNode, destNode, null, null);
    }

    private List<List<Edge>> walker(Node sourceNode, Node destNode, List<Edge> currentPath, List<List<Edge>> paths) {
        if (currentPath == null) {
            currentPath = new ArrayList<>();
        }
        if (paths == null) {
            paths = new ArrayList<>();
        }

        if (sourceNode.equals(destNode)) {
            throw new APIBadRequestException("O Ponto de Origem e de Destino não podem ser os mesmos");
        }

        List<Edge> edges = findByNodeA(sourceNode);

        for (Edge edge : edges) {
            if (!currentPath.contains(edge)&&!containsBidirectionalRef(currentPath,edge)) {
                List<Edge> forkPath = (List<Edge>) ((ArrayList<Edge>) currentPath).clone();

                forkPath.add(edge);

                if (edge.getNodeB().equals(destNode)) {
                    paths.add(forkPath);
                } else {
                    walker(edge.getNodeB(), destNode,forkPath,paths);
                }
            } 
        }

        return paths;
    }

    private boolean containsBidirectionalRef(List<Edge> edges, Edge nextEdge) {
        if (!edges.isEmpty()) {
            Edge last = edges.get(edges.size() - 1);
            return last.getNodeA().equals(nextEdge.getNodeB()) && nextEdge.getNodeA().equals(last.getNodeB());
        }
        return false;
    }
;

}
