package priceobserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * To start the application run this class main method.
 * By default, the application starts on 8080 port.
 *
 * Note: do not forget to specify DB credentials at core module resources/application.properties
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
