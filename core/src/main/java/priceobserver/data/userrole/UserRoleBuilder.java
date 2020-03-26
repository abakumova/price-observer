package priceobserver.data.userrole;

public final class UserRoleBuilder {
    private Byte id;
    private String name;

    private UserRoleBuilder() {
    }

    public static UserRoleBuilder anUserRole() {
        return new UserRoleBuilder();
    }

    public UserRoleBuilder withId(Byte id) {
        this.id = id;
        return this;
    }

    public UserRoleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserRole build() {
        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setName(name);
        return userRole;
    }
}
