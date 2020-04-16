package priceobserver.data.store;

public final class StoreBuilder {
    private Long id;
    private String name;
    private String url;

    private StoreBuilder() {
    }

    public static StoreBuilder aStore() {
        return new StoreBuilder();
    }

    public StoreBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StoreBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public StoreBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public Store build() {
        Store store = new Store();
        store.setId(id);
        store.setName(name);
        store.setUrl(url);
        return store;
    }
}
