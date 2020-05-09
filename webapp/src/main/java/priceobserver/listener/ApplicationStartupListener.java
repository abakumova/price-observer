package priceobserver.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import priceobserver.avic.AvicParsingManager;

import static priceobserver.configuration.PropertiesNames.IS_STARTUP_PARSING_ENABLED;

@Component
@Order(0)
@PropertySource("classpath:base.properties")
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartupListener.class);

    private final AvicParsingManager avicParsingManager;
    private final Environment env;

    @Autowired
    public ApplicationStartupListener(AvicParsingManager avicParsingManager, Environment env) {
        this.avicParsingManager = avicParsingManager;
        this.env = env;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (Boolean.parseBoolean(env.getProperty(IS_STARTUP_PARSING_ENABLED.getName()))) {
            LOGGER.warn("Startup product parsing enabled. Starting parsing");
            avicParsingManager.loadProducts(avicParsingManager.parsePages());
        }
    }
}
