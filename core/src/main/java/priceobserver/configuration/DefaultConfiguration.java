package priceobserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:base.properties")
public class DefaultConfiguration {
}
