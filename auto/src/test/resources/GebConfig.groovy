import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

reportsDir = new File("target/geb-reports")
reportOnTestFailureOnly = true

baseUrl = "https://pn.com.ua/"

System.setProperty("webdriver.chrome.driver", System.getProperty("webdriver.chrome.driver", "target/driver/chromedriver.exe"))

ChromeOptions options = new ChromeOptions()
options.addArguments("--no-sandbox")
options.addArguments("--start-maximized")
options.addArguments("--disable-notifications")

driver = { new ChromeDriver(options) }