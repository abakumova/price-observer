package priceobserver.dto.manufacturer;

public final class ManufacturerDtoBuilder {
    private Long id;
    private String name;
    private String country;
    private Long version;

    private ManufacturerDtoBuilder() {
    }

    public static ManufacturerDtoBuilder aManufacturerDto() {
        return new ManufacturerDtoBuilder();
    }

    public ManufacturerDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ManufacturerDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ManufacturerDtoBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public ManufacturerDtoBuilder withVersion(Long version) {
        this.version = version;
        return this;
    }

    public ManufacturerDto build() {
        ManufacturerDto manufacturerDto = new ManufacturerDto();
        manufacturerDto.setId(id);
        manufacturerDto.setName(name);
        manufacturerDto.setCountry(country);
        manufacturerDto.setVersion(version);
        return manufacturerDto;
    }
}
