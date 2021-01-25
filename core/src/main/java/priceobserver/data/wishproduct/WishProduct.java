package priceobserver.data.wishproduct;

import priceobserver.data.product.Product;
import priceobserver.data.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "wish_product")
public class WishProduct {

    @Id
    @Positive
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Boolean isDeleted = false;

    @NotNull
    @PastOrPresent
    private LocalDate dateAdded;

    @Version
    private Integer version;

    /**
     * ManyToOne because one WishProduct row represents a product added to wish list by a user.
     * If you need to review a full user's wish list just use a SELECT by user_id and is_deleted = false.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "WishProduct{" +
                "id=" + id +
                ", isDeleted=" + isDeleted +
                ", dateAdded=" + dateAdded +
                ", user=" + user +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishProduct wishProduct = (WishProduct) o;
        return Objects.equals(id, wishProduct.id) &&
                Objects.equals(isDeleted, wishProduct.isDeleted) &&
                Objects.equals(dateAdded, wishProduct.dateAdded) &&
                Objects.equals(version, wishProduct.version) &&
                Objects.equals(user, wishProduct.user) &&
                Objects.equals(product, wishProduct.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isDeleted, dateAdded, version, user, product);
    }
}