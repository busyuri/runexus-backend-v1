package infrastructure.persistence.repositories;

import domain.models.Role;
import domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findByRole(Role role);

    List<User> findByPaceBetween(double minPace, double maxPace);
}
