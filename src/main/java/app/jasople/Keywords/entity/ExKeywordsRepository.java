package app.jasople.Keywords.entity;

import app.jasople.Experience.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExKeywordsRepository extends JpaRepository<ExperienceKeywords,Long> {

    List<ExperienceKeywords> findByExperience(Experience experience);


    int deleteByExperience(Experience experience);

    void deleteAllByExperience(Experience ex);
}
