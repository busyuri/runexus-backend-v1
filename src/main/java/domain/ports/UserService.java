package domain.ports;

import domain.models.Role;
import domain.models.User;

import java.util.List;


public interface UserService {
    User registerUser(User user);
    User loginUser(String email, String password);
    boolean deleteUser(Long userId);
    User updateUser(Long userId, User user);
    User changePassword(Long userId, String oldPassword, String newPassword);

    List<User> getAllUsers();
    User getUserById(Long userId);
    List<User> getUsersByRole(Role role);
    List<User> getUsersByPace(double pace);

}
