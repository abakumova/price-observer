package driver

import driver.properties.PropertyHolder

import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

abstract class Driver extends Script {

    static def chromeVersion = PropertyHolder.chrome.getChromeVersion()
    static def destinationFolder = "target/chromedriver"
    static def fileName = "chromedriver_win32.zip"
    static def chromeDriverDomain = "https://chromedriver.storage.googleapis.com/"

    static void main(String[] args) {
        createChromeDriver()
    }

    def static createChromeDriver() {
        def pathToZip = destinationFolder + "/" + fileName
        createDestDirectoryIfNotExists(destinationFolder)
        saveFile(new URL(chromeDriverDomain + chromeVersion + "/" + fileName), pathToZip)
        unzipChromeDriver(pathToZip, destinationFolder)
        deleteZipArchive(pathToZip)
    }

    static def saveFile(URL url, String filePath) {
        ReadableByteChannel rbc = Channels.newChannel(url.openStream())
        FileOutputStream fos = new FileOutputStream(filePath)
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE)
        rbc.close()
        fos.close()
    }

    static def createDestDirectoryIfNotExists(String destinationFolderPath) {
        File directoryToSave = new File(destinationFolderPath)
        if (!directoryToSave.exists()) {
            directoryToSave.mkdirs()
        }
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
    }
}