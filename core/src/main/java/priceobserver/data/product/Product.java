package priceobserver.data.product;

import priceobserver.data.manufacturer.Manufacturer;
import priceobserver.data.productproperties.ProductProperties;
import priceobserver.data.producttype.ProductType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;

    private String description;

    @NotNull
    private String model;

    private Integer year;

    /**
     * Describes a url to the image for current product.
     */
    private String image;

    @ManyToOne
    @JoinColumn(name = "properties_id", nullable = false)
    private ProductProperties productProperties;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductProperties getProductProperties() {
        return productProperties;
    }

    public void setProductProperties(ProductProperties productProperties) {
        this.productProperties = productProperties;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", image='" + image + '\'' +
                ", productProperties=" + productProperties +
                ", productType=" + productType +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
