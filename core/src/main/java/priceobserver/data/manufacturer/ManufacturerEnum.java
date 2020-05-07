package priceobserver.data.manufacturer;

import java.util.Arrays;
import java.util.Optional;

public enum ManufacturerEnum {

    APPLE(1, "Apple", "USA"),
    SAMSUNG(2, "Samsung", "South Korea"),
    XIAOMI(3, "Xiaomi", "China"),
    HUAWEI(4, "Huawei", "China"),
    MEIZU(5, "Meizu", "China"),
    ONEPLUS(6, "OnePlus", "China"),
    PHILIPS(7, "Philips", "Netherlands"),
    BOSCH(8, "Bosch", "Germany"),
    ASUS(9, "Asus", "Taiwan"),
    ACER(10, "Acer", "Taiwan"),
    LG(11, "LG", "South Korea"),
    LENOVO(12, "Lenovo", "Hong Kong"),
    HP(13, "HP", "USA"),
    DELL(14, "Dell", "USA"),
    MICROSOFT(15, "Microsoft", "USA"),
    JBL(16, "JBL", "USA"),
    SONY(17, "Sony", "Japan");

    private final long id;
    private final String name;
    private final String country;

    ManufacturerEnum(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public static Optional<ManufacturerEnum> getById(int id) {
        return Arrays.stream(ManufacturerEnum.values())
                .filter(m -> m.id == id)
                .findFirst();
    }

    public static Optional<ManufacturerEnum> getByName(String name) {
        return Arrays.stream(ManufacturerEnum.values())
                .filter(m -> m.name.equals(name))
                .findFirst();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
