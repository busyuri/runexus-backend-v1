package infrastructure.persistence.adapters;

import domain.models.Role;
import domain.models.User;
import domain.ports.UserService;
import infrastructure.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        user.setRole(Role.REGISTERED);
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }

    @Override
    public boolean deleteUser(Long userId){
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public User updateUser(Long userId, User user) {
        User user1 = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));

        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setBirthday(user.getBirthday());
        user1.setGender(user.getGender());
        user1.setPace(user.getPace());
        return userRepository.save(user1);
    }

    @Override
    public User changePassword(Long userId, String oldPassword, String newPassword) {
        User user = getUserById(userId);

        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Old password does not match.");
        }

        user.setPassword(newPassword);
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> getUsersByPace(double pace) {
        double tolerance = 0.3;
        return userRepository.findByPaceBetween(pace - tolerance, pace + tolerance);
    }

}
