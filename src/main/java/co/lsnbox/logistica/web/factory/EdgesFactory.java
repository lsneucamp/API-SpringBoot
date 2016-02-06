package co.lsnbox.logistica.web.factory;

import co.lsnbox.logistica.domain.Edge;
import co.lsnbox.logistica.web.dto.EdgeDTO;
import java.util.List;
import java.util.stream.Collectors;

public class EdgesFactory {
    
    public static EdgeDTO toDTO(Edge edge){
        return new EdgeDTO(edge.getId(),
                NodeFactory.toDTO(edge.getNodeA()),
                NodeFactory.toDTO(edge.getNodeB()),
                edge.getDistance());
    }
    
    public static List<EdgeDTO> toDTOs(List<Edge> edges){
        return edges.stream().map(EdgesFactory::toDTO).collect(Collectors.toList());
    }
    
}
