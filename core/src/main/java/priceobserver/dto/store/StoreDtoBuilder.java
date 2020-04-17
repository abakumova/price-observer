package priceobserver.dto.store;

public final class StoreDtoBuilder {
    private Long id;
    private String name;
    private String url;

    private StoreDtoBuilder() {
    }

    public static StoreDtoBuilder aStoreDto() {
        return new StoreDtoBuilder();
    }

    public StoreDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StoreDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StoreDtoBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public StoreDto build() {
        StoreDto storeDto = new StoreDto();
        storeDto.setId(id);
        storeDto.setName(name);
        storeDto.setUrl(url);
        return storeDto;
    }
}
