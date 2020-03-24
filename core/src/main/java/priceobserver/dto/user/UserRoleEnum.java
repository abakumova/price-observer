package priceobserver.dto.user;

public enum UserRoleEnum {

    GOD("god"),
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
