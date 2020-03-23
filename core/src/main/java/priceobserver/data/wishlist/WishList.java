package priceobserver.data.wishlist;

import priceobserver.data.product.Product;
import priceobserver.data.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Column(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @ManyToOne
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

    /*
     * Generated with Builder Generator plugin https://plugins.jetbrains.com/plugin/6585-builder-generator
     * Just testing it, looks better than Lombok, cause we don't need to specify any external library.
     */
    public static final class WishListBuilder {
        private Long id;
        private Boolean isDeleted;
        private Date dateAdded;
        private User user;
        private Product product;

        private WishListBuilder() {
        }

        public static WishListBuilder aWishList() {
            return new WishListBuilder();
        }

        public WishListBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public WishListBuilder withIsDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public WishListBuilder withDateAdded(Date dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public WishListBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public WishListBuilder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public WishList build() {
            WishList wishList = new WishList();
            wishList.setId(id);
            wishList.setDateAdded(dateAdded);
            wishList.setUser(user);
            wishList.setProduct(product);
            wishList.isDeleted = this.isDeleted;
            return wishList;
        }
    }
}
