package app.jasople.IndustryInfo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IndustryInfoRepository extends JpaRepository<IndustryInfo,Long> {

    List<IndustryInfo> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);
}
