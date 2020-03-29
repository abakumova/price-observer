package priceobserver.dto.product;

import priceobserver.dto.manufacturer.ManufacturerDto;
import priceobserver.dto.productproperties.ProductPropertiesDto;
import priceobserver.dto.producttype.ProductTypeDto;

import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String model;
    private Year year;
    private String image;
    private Integer version;
    private ProductPropertiesDto productProperties;
    private ProductTypeDto productType;
    private ManufacturerDto manufacturer;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public ProductPropertiesDto getProductProperties() {
        return productProperties;
    }

    public void setProductProperties(ProductPropertiesDto productProperties) {
        this.productProperties = productProperties;
    }

    public ProductTypeDto getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeDto productType) {
        this.productType = productType;
    }

    public ManufacturerDto getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", image='" + image + '\'' +
                ", version=" + version +
                ", productProperties=" + productProperties +
                ", productType=" + productType +
                ", manufacturer=" + manufacturer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(model, that.model) &&
                Objects.equals(year, that.year) &&
                Objects.equals(image, that.image) &&
                Objects.equals(version, that.version) &&
                Objects.equals(productProperties, that.productProperties) &&
                Objects.equals(productType, that.productType) &&
                Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, model, year, image, version, productProperties, productType, manufacturer);
    }
}
