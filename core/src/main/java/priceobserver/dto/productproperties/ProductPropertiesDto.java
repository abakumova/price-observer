package priceobserver.dto.productproperties;

import java.io.Serializable;
import java.util.Objects;

public class ProductPropertiesDto implements Serializable {

    private Long id;
    private String properties;
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ProductPropertiesDto{" +
                "id=" + id +
                ", properties='" + properties + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPropertiesDto that = (ProductPropertiesDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(properties, that.properties) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, properties, version);
    }
}
