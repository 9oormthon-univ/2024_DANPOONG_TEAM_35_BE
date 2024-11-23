package app.jasople.Category.entity;

import app.jasople.Experience.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);

}
