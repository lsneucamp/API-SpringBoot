package co.lsnbox.logistica.web.controllers.v1;

import co.lsnbox.logistica.AbstractTest;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EdgeControllerTest extends AbstractTest {

    @Test
    public void findAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/edges/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findOne() throws Exception {
        String edgeId = "54f6612bb0e6af1800b5dE0A".toLowerCase();

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/edges/" + edgeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._id", equalTo(edgeId)));

    }

    @Test
    public void findPathsAll() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/edges/")
                .param("paths", "all")
                .param("sourceNodeId", "54f6612bb0e6af1800b5d00a")
                .param("destNodeId", "54f6612bb0e6af1800b5d00e")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].distance", Matchers.contains(55.0, 60.0, 80.0)));

    }

    @Test
    public void findPathsBest() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/edges/")
                .param("paths", "best")
                .param("sourceNodeId", "54f6612bb0e6af1800b5d00a")
                .param("destNodeId", "54f6612bb0e6af1800b5d00e")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].distance").value(55.0));
    }

    @Test
    public void findPathsWorst() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/edges/")
                .param("paths", "worst")
                .param("sourceNodeId", "54f6612bb0e6af1800b5d00a")
                .param("destNodeId", "54f6612bb0e6af1800b5d00e")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].distance").value(80.0));

    }

    @Test
    public void findPathsAllWithCost() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/edges/")
                .param("paths", "all")
                .param("sourceNodeId", "54f6612bb0e6af1800b5d00a")
                .param("destNodeId", "54f6612bb0e6af1800b5d00e")
                .param("autonomy", "12.0")
                .param("fuelCost", "3.5")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].cost", Matchers.contains((55.0/12.0)*3.5,(60.0/12.0)*3.5, (80.0/12.0)*3.5)));

    }

    @Test
    public void findPathsBestWithCost() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/edges/")
                .param("paths", "best")
                .param("sourceNodeId", "54f6612bb0e6af1800b5d00a")
                .param("destNodeId", "54f6612bb0e6af1800b5d00e")
                .param("autonomy", "12.0")
                .param("fuelCost", "3.5")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].cost").value((55.0/12)*3.5));
    }

    @Test
    public void findPathsWorstWithCost() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/edges/")
                .param("paths", "worst")
                .param("sourceNodeId", "54f6612bb0e6af1800b5d00a")
                .param("destNodeId", "54f6612bb0e6af1800b5d00e")
                .param("autonomy", "12.0")
                .param("fuelCost", "3.5")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].cost").value((80.0/12.0)*3.5));

    }

}
