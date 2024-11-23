package app.jasople.Keywords.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordsRepository extends JpaRepository<Keywords,Long> {

    List<Keywords> findByKeywordType(keywordType keywordType);

    boolean existsByName(String name);
}
