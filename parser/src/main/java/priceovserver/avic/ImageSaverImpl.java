package priceovserver.avic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import priceovserver.ImageSaver;

import java.io.File;
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

    private static final String DEFAULT_FOLDER_PATH = "webapp/src/main/resources/static/assets/productImages";

    @Override
    public Optional<String> saveImageByUrl(String url, String saveDir) {
        if (url == null || saveDir == null) {
            LOGGER.error("url or save dir can't be null! url = {}, saveDir = {}", url, saveDir);
            throw new IllegalArgumentException();
        }

        String pathToFile = getPathToFile(url, saveDir);
        createSaveDirectoryIfNotExists(saveDir);
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

    @Override
    public Optional<String> saveImageByUrlToDefaultFolder(String url) {
        return saveImageByUrl(url, DEFAULT_FOLDER_PATH);
    }

    @Override
    public String getDefaultFolderPath() {
        return DEFAULT_FOLDER_PATH;
    }

    private String getPathToFile(String url, String saveDir) {
        String[] splitUrl = url.split("/");
        String fileName = splitUrl[splitUrl.length - 1];
        return saveDir.isEmpty() ? fileName : new StringBuilder(saveDir).append("/").append(fileName).toString();
    }

    private void createSaveDirectoryIfNotExists(String dirPath) {
        if (dirPath.isEmpty()) {
            return;
        }

        File saveDirectory = new File(dirPath);
        if (!saveDirectory.exists()) {
            saveDirectory.mkdirs();
        }
    }
}
