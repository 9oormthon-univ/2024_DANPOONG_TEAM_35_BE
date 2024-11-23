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
                "학업 및 학문적 경험", "대외활동", "실무 경험", "사회공헌 활동",
                "자기 개발", "조직 및 리더십 경험", "기타 유용한 경험"
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

