package driver

import driver.properties.PropertyHolder
import groovyx.net.http.RESTClient
import spock.lang.Shared

import java.util.zip.ZipFile

class Driver {

    @Shared
    def client = new RESTClient("https://chromedriver.storage.googleapis.com")

    def chrome = PropertyHolder.chrome.getChromeVersion()
    def outputDir = "src/target/driver"

    def getBrowserWithVersion() {
        //def response =  new File("target/driver/chrome")
        def response = client.get(path: "/" + chrome + "/chromedriver_win32.zip")
        if (response.status == 200) {
            save() // target/driver/
            unzip()  // target/driver/
        }
    }

    def save() {

    }

    def unzip() {
        def zip = new ZipFile(new File(zipFileName))
        zip.entries().each {
            if (!it.isDirectory()) {
                def fOut = new File(outputDir + File.separator + it.name)
                //create output dir if not exists
                new File(fOut.parent).mkdirs()
                def fos = new FileOutputStream(fOut)
                //println "name:${it.name}, size:${it.size}"
                def buf = new byte[it.size]
                def len = zip.getInputStream(it).read(buf) //println zip.getInputStream(it).text
                fos.write(buf, 0, len)
                fos.close()
            }
        }
        zip.close()
    }
}