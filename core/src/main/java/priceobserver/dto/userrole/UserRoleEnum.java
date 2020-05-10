package priceobserver.dto.userrole;

public enum UserRoleEnum {

    ADMIN("admin", (byte) 1),
    USER("user", (byte) 2),
    GOD("god", (byte) 3);

    private final String role;
    private final Byte id;

    UserRoleEnum(String role, Byte id) {
        this.role = role;
        this.id = id;
    }

    public String getRole() {
        return this.role;
    }

    public Byte getId() {
        return this.id;
    }
}
