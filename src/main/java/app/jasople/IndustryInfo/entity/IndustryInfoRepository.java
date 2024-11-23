package app.jasople.IndustryInfo.entity;

import app.jasople.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IndustryInfoRepository extends JpaRepository<IndustryInfo,Long> {

    List<IndustryInfo> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);

    boolean existsByTitle(String title);
}
