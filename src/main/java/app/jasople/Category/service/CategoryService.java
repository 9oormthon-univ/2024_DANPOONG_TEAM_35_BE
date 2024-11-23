package app.jasople.Category.service;


import app.jasople.Category.dto.CategoryResponseDto;
import app.jasople.Category.entity.Category;
import app.jasople.Category.entity.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 리스트 조회
    @Transactional
    public List<CategoryResponseDto> viewCategory() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(CategoryResponseDto::new).collect(Collectors.toList());
    }

}
