package co.lsnbox.logistica.repository.mongo;

import co.lsnbox.logistica.domain.Node;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface NodeRepository extends MongoRepository<Node, String>{
    
}
