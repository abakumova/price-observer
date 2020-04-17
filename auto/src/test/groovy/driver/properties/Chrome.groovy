package driver.properties

import org.aeonbits.owner.Config
import org.aeonbits.owner.Config.Key
import org.aeonbits.owner.Config.Sources

@Sources("classpath:chrome.properties")
interface Chrome extends Config {

    @Key("chrome81")
    String getChromeVersion()
}
