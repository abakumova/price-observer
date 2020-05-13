package priceobserver.configuration;

public enum PropertiesNames {
    IS_STARTUP_PRODUCT_PARSING_ENABLED("options.startup-product-parsing.enabled"),
    IS_STARTUP_PRICE_PARSING_ENABLED("options.startup-price-parsing.enabled");

    private final String name;

    PropertiesNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
