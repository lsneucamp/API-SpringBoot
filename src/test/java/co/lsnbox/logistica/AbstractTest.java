package co.lsnbox.logistica;

import co.lsnbox.logistica.config.ApplicationTest;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by luciano on 23/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTest.class)
@WebAppConfiguration
public abstract  class AbstractTest {
   

    @Inject
    private WebApplicationContext webApplicationContext;

//    @Inject
//    private FilterChainProxy springSecurityFilterChain;

    protected MockMvc mvc;

    protected ObjectMapper mapper;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, false);
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        mvc = webAppContextSetup(webApplicationContext).build();
    }



}
