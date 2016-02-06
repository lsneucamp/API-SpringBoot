package co.lsnbox.logistica.web.controllers.v1;

import co.lsnbox.logistica.AbstractTest;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NodeControllerTest extends AbstractTest {

    @Test
    public void findAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/nodes/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findOne() throws Exception {
        String nodeId = "54f6612bb0e6af1800b5d00a";

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/nodes/" + nodeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._id", equalTo(nodeId)));

    }

}
