package priceobserver.configuration;

public enum PropertiesNames {
    IS_STARTUP_PARSING_ENABLED("options.startup-parsing.enabled");

    private final String name;

    PropertiesNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
