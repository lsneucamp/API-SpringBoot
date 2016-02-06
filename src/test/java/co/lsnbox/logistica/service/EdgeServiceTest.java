package co.lsnbox.logistica.service;

import co.lsnbox.logistica.AbstractTest;
import co.lsnbox.logistica.domain.Edge;
import co.lsnbox.logistica.domain.Node;
import co.lsnbox.logistica.service.interfaces.IEdgeService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import static org.hamcrest.Matchers.*;

public class EdgeServiceTest extends AbstractTest {

    @Inject
    IEdgeService edgeService;

    @Test
    public void find_ByNodeA_ShouldGetListOfEdges() throws Exception {
        List<Edge> edges = edgeService.findByNodeA(new Node("54f6612bb0e6af1800b5d00a"));
        Assert.assertNotNull(edges);
        Assert.assertThat(edges, not(empty()));
    }
    
    @Test
    public void findPaths_BySourceNodeAndDestNode_ShouldGetListAllPossiblePaths() throws Exception {
        Node sourceNode = new Node("54f6612bb0e6af1800b5d00a");
        Node destNode = new Node("54f6612bb0e6af1800b5d00e");
        
        List<List<Edge>> paths = edgeService.walker(sourceNode,destNode);
        
        Assert.assertNotNull(paths);
        Assert.assertThat(paths, not(empty()));
    }

}
