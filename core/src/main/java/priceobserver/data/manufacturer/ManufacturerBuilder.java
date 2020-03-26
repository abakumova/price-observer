package priceobserver.data.manufacturer;

public final class ManufacturerBuilder {
    private Long id;
    private String name;
    private String country;
    private Integer version;

    private ManufacturerBuilder() {
    }

    public static ManufacturerBuilder aManufacturer() {
        return new ManufacturerBuilder();
    }

    public ManufacturerBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ManufacturerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ManufacturerBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public ManufacturerBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Manufacturer build() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(id);
        manufacturer.setName(name);
        manufacturer.setCountry(country);
        manufacturer.setVersion(version);
        return manufacturer;
    }
}
