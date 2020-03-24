package priceobserver.data.user;

import priceobserver.data.userrole.UserRole;

import java.sql.Date;

public final class UserBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birth;
    private String encryptedPassword;
    private String password;
    private Integer version;
    private UserRole userRole;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withBirth(Date birth) {
        this.birth = birth;
        return this;
    }

    public UserBuilder withEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public UserBuilder withUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirth(birth);
        user.setEncryptedPassword(encryptedPassword);
        user.setPassword(password);
        user.setVersion(version);
        user.setUserRole(userRole);
        return user;
    }
}
