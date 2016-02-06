package co.lsnbox.logistica.service;

import co.lsnbox.logistica.domain.Node;
import co.lsnbox.logistica.repository.mongo.NodeRepository;
import co.lsnbox.logistica.service.interfaces.INodeService;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl implements INodeService {

    private static Logger LOG = LoggerFactory.getLogger(NodeServiceImpl.class);

    @Inject
    NodeRepository nodeRepository;

    @Override
    public Node findOne(String id) {
        return nodeRepository.findOne(id);
    }

    @Override
    public List<Node> findAll() {
        return nodeRepository.findAll();
    }

}
