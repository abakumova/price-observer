package priceobserver.dto.store;

import java.util.Arrays;
import java.util.Optional;

public enum StoreEnum {

    AVIC(1, "Avic", "https://avic.ua/"),
    ALLO(2, "Allo", "https://allo.ua/"),
    ROZETKA(3, "Rozetka", "https://rozetka.com.ua/"),
    CITRUS(4, "Citrus", "https://www.citrus.ua/"),
    CACTUS(5, "Cactus", "https://www.c.ua/"),
    MOYO(6, "Moyo", "https://www.moyo.ua/"),
    FOXTROT(7, "Foxtrot", "https://www.foxtrot.com.ua/"),
    COMFY(8, "Comfy", "https://comfy.ua/"),
    ELDORADO(9, "Eldorado", "https://eldorado.ua/");

    private final long id;
    private final String name;
    private final String url;

    StoreEnum(long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public static Optional<StoreEnum> getById(long id) {
        return Arrays.stream(StoreEnum.values())
                .filter(s -> s.id == id)
                .findFirst();
    }

    public static Optional<StoreEnum> getByName(String name) {
        return Arrays.stream(StoreEnum.values())
                .filter(s -> s.name.equals(name))
                .findFirst();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
