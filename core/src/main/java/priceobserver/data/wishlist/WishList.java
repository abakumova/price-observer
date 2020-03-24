package priceobserver.data.wishlist;

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
import java.sql.Date;

@Entity
@Table(name = "wish_list")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Boolean isDeleted;

    @NotNull
    private Date dateAdded;

    @Version
    private Integer version;

    /**
     * ManyToOne because one WishList row represents a product added to wish list by a user.
     * If you need to review a full user's wish list just use a SELECT by user_id and is_deleted = false.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
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
        return "WishList{" +
                "id=" + id +
                ", isDeleted=" + isDeleted +
                ", dateAdded=" + dateAdded +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
