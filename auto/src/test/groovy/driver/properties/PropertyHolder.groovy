package driver.properties

import org.aeonbits.owner.ConfigFactory

class PropertyHolder {

    static Chrome chrome = ConfigFactory.create(Chrome.class);
}