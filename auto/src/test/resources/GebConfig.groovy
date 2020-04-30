import org.codehaus.groovy.control.CompilerConfiguration
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import java.nio.file.Files
import java.nio.file.Paths

final webDriver = "webdriver.chrome.driver"

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

baseUrl = "https://pn.com.ua/" //todo change to our application Base Url

ChromeOptions options = new ChromeOptions()
options.addArguments("--no-sandbox")
options.addArguments("--start-maximized")
options.addArguments("--disable-notifications")

def path = Paths.get(getProperties().getProperty("destinationFolder"))

if (Files.notExists(path)) {
    def config = new CompilerConfiguration()
    config.scriptBaseClass = 'driver.Driver'

    def shell = new GroovyShell(this.class.classLoader, new Binding(), config)
    def script = shell.parse('createChromeDriver()')
    script.invokeMethod("main", null)
}

driver = {
    System.setProperty(webDriver, System.getProperty(webDriver, path.toString() + "/chromedriver.exe"))
    new ChromeDriver(options)
}

static Properties getProperties() {
    Properties properties = new Properties()
    properties.load(GebConfig.class.getResourceAsStream('/paths.properties'))
    properties
}