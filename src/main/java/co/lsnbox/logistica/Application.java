package co.lsnbox.logistica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@SpringBootApplication
public class Application   implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
      
    }

    @Bean
    public Validator validator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }



}