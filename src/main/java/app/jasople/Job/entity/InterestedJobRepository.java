package app.jasople.Job.entity;

import app.jasople.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestedJobRepository extends JpaRepository<InterestedJob,Long> {
    InterestedJob findByUser(User user);
}
