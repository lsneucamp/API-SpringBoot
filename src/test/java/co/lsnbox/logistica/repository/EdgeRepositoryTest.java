package co.lsnbox.logistica.repository;

import co.lsnbox.logistica.AbstractTest;
import co.lsnbox.logistica.domain.Edge;
import co.lsnbox.logistica.domain.Node;
import co.lsnbox.logistica.repository.mongo.EdgeRepository;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import static org.hamcrest.Matchers.*;


public class EdgeRepositoryTest extends AbstractTest {

    @Inject
    EdgeRepository edgeRepository;

    @Test
    public void find_ByNodeA_ShouldGetListOfEdges() throws Exception {
        List<Edge> edges = edgeRepository.findByNodeA(new Node("54f6612bb0e6af1800b5d00a"));
        Assert.assertNotNull(edges);
        Assert.assertThat(edges, not(empty()));
    }

}
