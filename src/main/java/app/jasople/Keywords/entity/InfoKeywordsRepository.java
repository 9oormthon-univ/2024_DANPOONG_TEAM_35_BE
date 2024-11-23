package app.jasople.Keywords.entity;

import app.jasople.IndustryInfo.entity.ScrapedInfo;
import app.jasople.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InfoKeywordsRepository extends JpaRepository<InfoKeywords,Long> {
   List<InfoKeywords> findByScrapedInfo(ScrapedInfo info);

}
