package priceovserver.avic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import priceovserver.ImageSaver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Optional;

@Component
public class ImageSaverImpl implements ImageSaver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageSaverImpl.class);

    @Override
    public Optional<String> saveImageByUrl(String url, String saveDir) {
        String pathToFile = getPathToFile(url, saveDir);
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathToFile);
             ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream())) {

            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (MalformedURLException e) {
            LOGGER.error("MalformedURLException: url = {}, saveDir = {} ", url, saveDir, e);
            return Optional.empty();
        } catch (IOException e) {
            LOGGER.error("IOException: url = {}, saveDir = {} ", url, saveDir, e);
            return Optional.empty();
        }
        return Optional.of(pathToFile);
    }

    private String getPathToFile(String url, String saveDir) {
        String[] splitUrl = url.split("/");
        String fileName = splitUrl[splitUrl.length - 1];
        return new StringBuilder(saveDir).append("/").append(fileName).toString();
    }
}
