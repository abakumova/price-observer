package priceobserver.dto.producttype;

import java.util.Arrays;
import java.util.Optional;

public enum ProductTypeEnum {

    SMARTPHONE(1, "smartphone"),
    LAPTOP(2, "laptop"),
    TABLET(3, "tablet"),
    SMARTWATCH(4, "smartwatch"),
    EARPHONES(5, "earphones"),
    TV(6, "TV"),
    ALL_IN_ONE(7, "all-in-one");

    private final long id;
    private final String name;

    ProductTypeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Optional<ProductTypeEnum> getById(long id) {
        return Arrays.stream(ProductTypeEnum.values())
                .filter(pt -> pt.id == id)
                .findFirst();
    }

    public static Optional<ProductTypeEnum> getByName(String name) {
        return Arrays.stream(ProductTypeEnum.values())
                .filter(pt -> pt.name.equals(name))
                .findFirst();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProductTypeEnum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
