package priceobserver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public String getModel(String fullProductName) {
        String modelRegex = "\\([MZ].*\\)";
        Matcher matcher = Pattern.compile(modelRegex).matcher(fullProductName);
        String model = null;
        while (matcher.find()) {
            model = fullProductName.substring(matcher.start(), matcher.end())
                    .replaceAll(ROUND_BRACES_REGEX, "");
        }
        return model;
    }
}
