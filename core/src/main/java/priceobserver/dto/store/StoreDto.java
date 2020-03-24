package priceobserver.dto.store;

import java.util.Objects;

public class StoreDto {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StoreDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreDto storeDto = (StoreDto) o;
        return id.equals(storeDto.id) &&
                name.equals(storeDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
