package app.jasople.Category.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryInitializer implements ApplicationRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // 카테고리명 리스트
        List<String> categoryNames = List.of(
                "자격증",
                "강의 수강",
                "연구 및 논문",
                "학과 프로젝트",
                "공모전 및 경진대회",
                "동아리 및 학회 활동",
                "교환학생/해외연수",
                "인턴십",
                "아르바이트",
                "프리랜서/개인 프로젝트",
                "봉사활동",
                "NGO 활동",
                "취미 활동",
                "개인 성장 스토리",
                "학생회 및 리더 활동",
                "캠페인 기획",
                "실패 경험",
                "일상 속 문제 해결"
        );

        // 카테고리명 리스트를 사용하여 Category 엔티티를 생성하고 저장
        categoryNames.forEach(name -> {
            if (!categoryRepository.existsByName(name)) {
                Category category = new Category(name);
                categoryRepository.save(category);
            }
        });
    }
}

