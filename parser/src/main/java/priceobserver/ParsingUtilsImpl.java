package priceobserver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParsingUtilsImpl implements ParsingUtils {

    @Override
    public Document getPageByUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ProductParsingException(String.format("Error during getting document by provided url. Url: %s ", url), e);
        }
    }
}
