package app.jasople.IndustryInfo.entity;

import app.jasople.Experience.entity.Experience;
import app.jasople.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapedInfoRepository extends JpaRepository<ScrapedInfo,Long> {

    List<ScrapedInfo> findByUser(User user);
}
