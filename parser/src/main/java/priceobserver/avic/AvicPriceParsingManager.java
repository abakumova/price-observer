package priceobserver.avic;

import org.springframework.stereotype.Component;
import priceobserver.PriceParser;
import priceobserver.PriceParsingManager;

import java.util.List;

@Component
public class AvicPriceParsingManager implements PriceParsingManager {

    private static final String PAGE_WITH_MACBOOKS = "https://avic.ua/macbook";
    private static final String PAGE_WITH_IPHONES = "https://avic.ua/iphone";
    private static final String PAGE_WITH_IPADS = "https://avic.ua/ipad";
    private static final String PAGE_WITH_IMACS = "https://avic.ua/imac";
    private static final String PAGE_WITH_APPLE_WATCH = "https://avic.ua/apple-watch-umnie-chasi";

    private final PriceParser priceParser;

    public AvicPriceParsingManager(PriceParser priceParser) {
        this.priceParser = priceParser;
    }

    @Override
    public void run() {
        List.of(PAGE_WITH_MACBOOKS,
                PAGE_WITH_APPLE_WATCH,
                PAGE_WITH_IMACS,
                PAGE_WITH_IPHONES,
                PAGE_WITH_IPADS).forEach(priceParser::parse);
    }
}
