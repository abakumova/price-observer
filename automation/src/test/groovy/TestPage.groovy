import geb.Page
import org.openqa.selenium.By

class TestPage extends Page {

    static content = {
        label(wait: "veryFast") { $(By.xpath("//li[@class='active']//span")) }
    }
}