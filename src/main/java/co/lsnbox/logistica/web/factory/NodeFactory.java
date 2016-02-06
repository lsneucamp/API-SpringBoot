package co.lsnbox.logistica.web.factory;

import co.lsnbox.logistica.domain.Node;
import co.lsnbox.logistica.web.dto.NodeDTO;
import java.util.List;
import java.util.stream.Collectors;

public class NodeFactory {
    
    public static NodeDTO toDTO(Node node){
        return new NodeDTO(node.getId(), node.getName());
    }
    
    public static List<NodeDTO> toDTOs(List<Node> nodes){
        return nodes.stream().map(NodeFactory::toDTO).collect(Collectors.toList());
    }
    
}
