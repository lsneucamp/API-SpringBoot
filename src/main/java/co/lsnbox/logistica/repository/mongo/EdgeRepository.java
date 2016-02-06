package co.lsnbox.logistica.repository.mongo;

import co.lsnbox.logistica.domain.Edge;
import co.lsnbox.logistica.domain.Node;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EdgeRepository extends MongoRepository<Edge, String>{
    
    List<Edge> findByNodeA(Node node);
    
}
