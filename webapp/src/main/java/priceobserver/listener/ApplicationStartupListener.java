package priceobserver.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import priceobserver.avic.AvicParsingManager;
import priceobserver.dto.product.ProductDto;

import java.util.List;

@Component
@Order(0)
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private final AvicParsingManager avicParsingManager;

    @Autowired
    public ApplicationStartupListener(AvicParsingManager avicParsingManager) {
        this.avicParsingManager = avicParsingManager;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<ProductDto> products = avicParsingManager.parsePages();
        System.out.println("Size " + products.size());
    }
}
