package app.jasople.Essay.entity;

import app.jasople.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EssayRepository extends JpaRepository<Essay,Long> {
    Optional<Object> findByIdAndUser(Long id, User user);

    List<Essay> findByUser(User user);
}
