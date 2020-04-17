package driver

import driver.properties.PropertyHolder
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class DriverTest extends Specification {

    @Shared
    def client = new RESTClient("https://chromedriver.storage.googleapis.com")

    def chrome = PropertyHolder.chrome.getChromeVersion()

    def "Verify response after getting chromedriver"() {
        given: "Get driver with set version"
        def response = client.get(path: "/" + chrome + "/chromedriver_win32.zip")

        expect: "Verify that we have get the file"
        response.status == 200
    }
}