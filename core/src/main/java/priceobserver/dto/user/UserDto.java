package priceobserver.dto.user;

import priceobserver.dto.userrole.UserRoleEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class UserDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birth;
    private String encryptedPassword;
    private String password;
    private Integer version;
    private String profileImage;
    private UserRoleEnum userRole;

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", version=" + version +
                ", profileImage='" + profileImage + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(birth, userDto.birth) &&
                Objects.equals(version, userDto.version) &&
                Objects.equals(profileImage, userDto.profileImage) &&
                userRole == userDto.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, birth, version, profileImage, userRole);
    }
}
