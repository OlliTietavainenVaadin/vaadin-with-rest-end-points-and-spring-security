package in.virit.sb.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "My API",
                version = "1.0",
                description = "API documentation"
        )
)
@SpringBootApplication
public class DemoApplication {

    // This method is for "production mode server" and might need a priming
    // build (mvn package) to be run directly.
    // Use DevModeDemoApplication during development to enable Livereload & Copilot
    public static void main(String[] args) {
        System.err.println("""
                    This app is for demo purposes only!
                    Start the local server using DevModeDemoApplication on the test sources 
                    or from command line with mvn spring-boot:test:run.
                """);
        // SpringApplication.run(DemoApplication.class, args);
    }

}
