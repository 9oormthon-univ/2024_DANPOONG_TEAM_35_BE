package app.jasople.Keywords.entity;

import app.jasople.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoKeywordsRepository extends JpaRepository<InfoKeywords,Long> {
    List<Keywords> findByUser(User user);
}
