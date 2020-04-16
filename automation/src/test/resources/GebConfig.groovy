import io.github.bonigarcia.wdm.ChromeDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

reportsDir = "target/geb-reports"
baseUrl = "https://pn.com.ua/"

ChromeDriverManager.getInstance().setup()
ChromeOptions options = new ChromeOptions()
options.addArguments("--no-sandbox")
options.addArguments("--start-maximized")
options.addArguments("--disable-notifications")

driver = { new ChromeDriver(options) }