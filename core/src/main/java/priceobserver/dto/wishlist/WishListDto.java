package priceobserver.dto.wishlist;

import priceobserver.dto.product.ProductDto;
import priceobserver.dto.user.UserDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class WishListDto implements Serializable {

    private Long id;
    private Boolean isDeleted;
    private LocalDate dateAdded;
    private Integer version;
    private ProductDto product;
    private UserDto user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "WishListDto{" +
                "id=" + id +
                ", isDeleted=" + isDeleted +
                ", dateAdded=" + dateAdded +
                ", version=" + version +
                ", product=" + product +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListDto that = (WishListDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(isDeleted, that.isDeleted) &&
                Objects.equals(dateAdded, that.dateAdded) &&
                Objects.equals(version, that.version) &&
                Objects.equals(product, that.product) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isDeleted, dateAdded, version, product, user);
    }
}
