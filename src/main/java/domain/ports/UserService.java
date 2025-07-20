package domain.ports;

import domain.enums.Role;
import domain.models.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User registerUser(User user);
    User loginUser(String email, String password);
    boolean deleteUser(Long userId);
    User updateUser(Long userId, User user);
    User changePassword(Long userId, String oldPassword, String newPassword);

    List<User> getAllUsers();
    Optional<User> getUserById(Long userId);
    List<User> getUserByRole(Role role);
    List<User> getUserByPace(double pace);

}
