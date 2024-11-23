package app.jasople.Category.controller;


import app.jasople.Category.dto.CategoryResponseDto;
import app.jasople.Category.service.CategoryService;
import app.jasople.Config.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Tag(name = "카테고리 API", description = "카테고리 API입니다.")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "카테고리 리스트 조회", description = "카테고리 리스트를 조회합니다.")
    @GetMapping("/list")
    public ApiResponse<List<CategoryResponseDto>> getCategoryList() {
        List<CategoryResponseDto> categoryList = categoryService.viewCategory();
        return ApiResponse.onSuccess(categoryList);
    }


}
