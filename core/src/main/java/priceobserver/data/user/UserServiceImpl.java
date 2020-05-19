package priceobserver.data.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import priceobserver.data.userrole.UserRole;
import priceobserver.data.userrole.UserRoleRepository;
import priceobserver.dto.user.UserDto;
import priceobserver.dto.user.UserDtoConverter;
import priceobserver.dto.userrole.UserRoleEnum;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final UserDtoConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository roleRepository, UserDtoConverter userConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userConverter = userConverter;
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Null or blank email");
        }
        return userRepository.findByEmail(email).map(userConverter::convertToDto);
    }

    @Override
    @Transactional
    public void updateUser(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        Optional<User> persistUserOpt = userRepository.findByEmail(user.getEmail());
        if (persistUserOpt.isPresent()) {
            User persistUser = persistUserOpt.get();
            persistUser.setFirstName(user.getFirstName());
            persistUser.setLastName(user.getLastName());
            persistUser.setBirth(user.getBirth());
            String password = user.getPassword();
            if (password != null && !password.isBlank()) {
                persistUser.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
            }
            userRepository.save(persistUser);
        }
    }

    @Override
    @Transactional
    public void saveNewUser(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException("Null user passed!");
        }
        Optional<UserRole> role = roleRepository.findById(UserRoleEnum.USER.getId());
        if (role.isPresent()) {
            User newUser = UserBuilder.anUser()
                    .withEmail(user.getEmail())
                    .withEncryptedPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()))
                    .withBirth(user.getBirth())
                    .withFirstName(user.getFirstName())
                    .withLastName(user.getLastName())
                    .withUserRole(role.get())
                    .build();
            userRepository.save(newUser);
        }
    }

    @Override
    public boolean isEmailUsed(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Null or blank email");
        }
        return userRepository.countAllByEmail(email) > 0;
    }
}
