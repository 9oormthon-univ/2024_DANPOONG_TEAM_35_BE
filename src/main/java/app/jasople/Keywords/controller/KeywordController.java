package app.jasople.Keywords.controller;

import app.jasople.Config.ApiResponse;
import app.jasople.Keywords.dto.KeywordResponseDto;
import app.jasople.Keywords.entity.keywordType;
import app.jasople.Keywords.service.KeywordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    // 업계소식 키워드 조회 API
    @GetMapping("/view-all-info")
    @Operation(summary = "업계소식 키워드 조회 API", description = "업계소식 키워드를 모두 조회합니다.")
    public ApiResponse<List<KeywordResponseDto>> findAllInfoKeywords() {
        List<KeywordResponseDto> keywords = keywordService.findKeywordsByType(keywordType.INFO);
        return ApiResponse.onSuccess(keywords);
    }

    // 경험시트 키워드 조회 API
    @GetMapping("/view-all-experience")
    @Operation(summary = "경험시트 키워드 조회 API", description = "경험시트 키워드를 모두 조회합니다.")
    public ApiResponse<List<KeywordResponseDto>> findExperienceKeywords() {
        List<KeywordResponseDto> keywords = keywordService.findKeywordsByType(keywordType.EXPERIENCE);
        return ApiResponse.onSuccess(keywords);
    }


}
