package app.jasople.Keywords.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KeywordInitializer implements ApplicationRunner {

    private final KeywordsRepository keywordRepository;

    @Autowired
    public KeywordInitializer(KeywordsRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        List<String> keywordNames = List.of(
                "팀워크", "열정", "분석력", "도전정신", "창의력", "문제해결능력",
                "리더십", "책임감", "학습 능력", "커뮤니케이션 능력", "적응력",
                "비판적 사고", "시간 관리 능력", "자기주도성", "목표 설정 및 달성 능력",
                "데이터 활용 능력", "계획 및 조직 능력", "대인 관계 능력", "신뢰성",
                "기술 활용 능력", "감수성", "창업정신", "위기관리 능력", "문화적 감수성",
                "협상력", "목표 달성 의지", "정확성", "융합적 사고", "설득력",
                "열린 마음", "공감 능력", "창조적 문제 해결"
        );

        // 키워드명 리스트를 사용하여 Keywords 엔티티를 생성하고 저장
        keywordNames.forEach(name -> {
            if (!keywordRepository.existsByName(name)) {
                Keywords keyword = new Keywords(name, keywordType.EXPERIENCE);
                keywordRepository.save(keyword);
            }
        });

        // 키워드명 리스트 - Info 타입
        List<String> infoKeywordNames = List.of(
                "업계최신의", "화제의", "고난이도의", "기초적인", "개인흥미", "연구경험"
        );

        // Info 키워드 저장
        infoKeywordNames.forEach(name -> {
            if (!keywordRepository.existsByName(name)) {
                Keywords keyword = new Keywords(name, keywordType.INFO);
                keywordRepository.save(keyword);
            }
        });
    }
}
