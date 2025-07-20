package infrastructure.persistence.service;

import domain.enums.Role;
import domain.models.User;
import infrastructure.mapper.UserMapper;
import domain.ports.UserService;
import infrastructure.persistence.entity.UserEntity;
import infrastructure.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public User registerUser(User user) {
        // Email zaten kayıtlı mı kontrolü
        if (userRepository.findByEmailAndPassword(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Şifreyi hashle vs. (güvenlik için)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Varsayılan rol atama (örneğin: USER)
        user.setRole(Role.USER);

        UserEntity saved = userRepository.save(userMapper.domainToEntity(user));
        return userMapper.entityToDomain(saved);
    }

    @Override
    public User loginUser(String email, String password) {
        return null;
    }

    @Override
    public boolean deleteUser(Long userId) {
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User updateUser(Long userId, User user) {
        UserEntity updatedUser = userMapper.domainToEntity(user);
        updatedUser.setUserid(userId);
        return userMapper.entityToDomain(userRepository.save(updatedUser));
    }

    @Override
    public User changePassword(Long userId, String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::entityToDomain);
    }

    @Override
    public List<User> getUserByRole(Role role) {
        return userRepository.findByRole(role)
                .stream()
                .map(userMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUserByPace(double pace) {
        return userRepository.findByPace(pace)
                .stream()
                .map(userMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
