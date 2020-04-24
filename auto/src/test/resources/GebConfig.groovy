import org.codehaus.groovy.control.CompilerConfiguration
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

import java.nio.file.Files
import java.nio.file.Paths

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

baseUrl = "https://pn.com.ua/"

ChromeOptions options = new ChromeOptions()
options.addArguments("--no-sandbox")
options.addArguments("--start-maximized")
options.addArguments("--disable-notifications")

def path = Paths.get('target/chromedriver')

if (Files.notExists(path)) {
    def config = new CompilerConfiguration()
    config.scriptBaseClass = 'driver.Driver'

    def shell = new GroovyShell(this.class.classLoader, new Binding(), config)
    def script = shell.parse('createChromeDriver()')
    script.invokeMethod("main", null)
}

driver = {
    System.setProperty("webdriver.chrome.driver", System.getProperty("webdriver.chrome.driver", "target/chromedriver/chromedriver.exe"))
    new ChromeDriver(options)
}