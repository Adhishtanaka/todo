package at.todo.services;

import at.todo.models.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel registerUser(String username, String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        UserModel user = new UserModel(username, email, encodedPassword);
        return userRepository.save(user);
    }

    public Optional<UserModel> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public boolean loginUser(UserModel user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
