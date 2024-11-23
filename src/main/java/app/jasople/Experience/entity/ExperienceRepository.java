package app.jasople.Experience.entity;

import app.jasople.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    List<Experience> findByUser(User user);
    Optional<Experience> findByUserAndId(User user,Long id);
}
