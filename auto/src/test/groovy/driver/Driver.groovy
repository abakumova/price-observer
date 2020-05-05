package driver

import driver.properties.PropertyHolder
import org.apache.logging.log4j.LogManager

import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

abstract class Driver extends Script {

    static def chromeVersion = PropertyHolder.chrome.getChromeVersion()
    static def destinationFolder = getProperties().getProperty("destinationFolder") //to save manually - getProperty("manualDestinationFolder")
    static def fileName = "chromedriver_win32.zip"
    static def chromeDriverDomain = "https://chromedriver.storage.googleapis.com/"

    static final def LOGGER = LogManager.getLogger()

    static void main(String[] args) {
        createChromeDriver()
    }

    def static createChromeDriver() {
        def pathToZip = destinationFolder + "/" + fileName
        createDestDirectoryIfNotExists(destinationFolder)
        saveFile(new URL(chromeDriverDomain + chromeVersion + "/" + fileName), pathToZip)
        unzipChromeDriver(pathToZip, destinationFolder)
        deleteZipArchive(pathToZip)
        LOGGER.info("The ${chromeVersion} version of ChromeDriver was downloaded into the ${destinationFolder} " +
                " directory. File name is: chromedriver.exe.")
    }

    static def saveFile(URL url, String filePath) {
        ReadableByteChannel rbc = Channels.newChannel(url.openStream())
        FileOutputStream fos = new FileOutputStream(filePath)
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE)
        rbc.close()
        fos.close()
        LOGGER.info("Save File to ${filePath}")
    }

    static def createDestDirectoryIfNotExists(String destinationFolderPath) {
        File directoryToSave = new File(destinationFolderPath)
        if (!directoryToSave.exists()) {
            directoryToSave.mkdirs()
        }
        LOGGER.info("Create directory if it does not exist ${destinationFolderPath}")
    }

    static def unzipChromeDriver(String filePath, String saveDirPath) {
        File destDir = new File(saveDirPath)
        byte[] buffer = new byte[1024]
        ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath))
        ZipEntry zipEntry = zis.getNextEntry()
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry)
            FileOutputStream fos = new FileOutputStream(newFile)
            int len
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len)
            }
            fos.close()
            zipEntry = zis.getNextEntry()
        }
        zis.closeEntry()
        zis.close()
        LOGGER.info("Unzip archive")
    }

    static def newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName())

        String destDirPath = destinationDir.getCanonicalPath()
        String destFilePath = destFile.getCanonicalPath()

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName())
        }

        destFile
    }

    static def deleteZipArchive(String pathToZip) {
        File zip = new File(pathToZip)
        zip.delete()
        LOGGER.info("Delete zip archive")
    }

    static Properties getProperties() {
        Properties properties = new Properties()
        properties.load(Driver.class.getResourceAsStream('/paths.properties'))
        properties
    }
}