package co.lsnbox.logistica.web.factory;

import co.lsnbox.logistica.domain.Edge;
import co.lsnbox.logistica.web.dto.EdgeDTO;
import co.lsnbox.logistica.web.dto.PathDTO;
import java.util.List;
import java.util.stream.Collectors;

public class PathsFactory {

    public static PathDTO toDTO(List<Edge> edges) {
        if(edges==null || edges.isEmpty()){
            return null;
        }
        List<EdgeDTO> edgesDTO = EdgesFactory.toDTOs(edges);
        
        return new PathDTO(edgesDTO, edgesDTO.stream().mapToDouble(EdgeDTO::getDistance).sum());
    }

    public static List<PathDTO> toDTOs(List<List<Edge>> paths) {
        if (paths == null || paths.isEmpty()) {
            return null;
        }
        return paths.stream().map(PathsFactory::toDTO).collect(Collectors.toList());
    }

}
