package co.lsnbox.logistica.config.loggingAspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    @Profile("dev")
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }
}
