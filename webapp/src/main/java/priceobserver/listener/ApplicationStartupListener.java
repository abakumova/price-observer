package priceobserver.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import priceobserver.avic.AvicPriceParsingManager;
import priceobserver.avic.AvicProductParsingManager;

import static java.lang.Boolean.parseBoolean;
import static priceobserver.configuration.PropertiesNames.IS_STARTUP_PRICE_PARSING_ENABLED;
import static priceobserver.configuration.PropertiesNames.IS_STARTUP_PRODUCT_PARSING_ENABLED;

@Component
@Order(0)
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartupListener.class);

    private final AvicProductParsingManager avicParsingManager;
    private final AvicPriceParsingManager avicPriceParsingManager;
    private final Environment env;

    @Autowired
    public ApplicationStartupListener(AvicProductParsingManager avicParsingManager,
                                      AvicPriceParsingManager avicPriceParsingManager,
                                      Environment env) {
        this.avicParsingManager = avicParsingManager;
        this.avicPriceParsingManager = avicPriceParsingManager;
        this.env = env;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (parseBoolean(env.getProperty(IS_STARTUP_PRODUCT_PARSING_ENABLED.getName()))) {
            LOGGER.warn("Startup product parsing enabled. Starting parsing");
            avicParsingManager.run();
            LOGGER.warn("Startup product parsing successfully finished");
        }
        if (parseBoolean(env.getProperty(IS_STARTUP_PRICE_PARSING_ENABLED.getName()))) {
            LOGGER.warn("Startup price parsing enabled. Starting parsing");
            avicPriceParsingManager.run();
            LOGGER.warn("Startup price parsing successfully finished");
        }
    }
}
