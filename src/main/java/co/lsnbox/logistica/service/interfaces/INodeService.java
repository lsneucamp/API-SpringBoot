package co.lsnbox.logistica.service.interfaces;

import co.lsnbox.logistica.domain.Node;
import java.util.List;

public interface INodeService {

    Node findOne(String id);
    
    List<Node> findAll();

}
