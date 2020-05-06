package priceobserver.dto.producttype;

import java.util.Arrays;
import java.util.Optional;

public enum ProductTypeEnum {

    SMARTPHONE(1, "smartphone"),
    LAPTOP(2, "laptop"),
    TABLET(3, "tablet"),
    SMARTWATCH(4, "smartwatch"),
    EARPHONES(5, "earphones"),
    TV(6, "TV");

    private final String name;
    private final int id;

    ProductTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Optional<ProductTypeEnum> getById(int id) {
        return Arrays.stream(ProductTypeEnum.values())
                .filter(pt -> pt.getId() == id)
                .findFirst();
    }

    public static Optional<ProductTypeEnum> getByName(String name) {
        return Arrays.stream(ProductTypeEnum.values())
                .filter(pt -> pt.getName().equals(name))
                .findFirst();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
