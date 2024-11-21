package app.jasople.Keywords.controller;

import app.jasople.Keywords.dto.KeywordResponseDto;
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

    @GetMapping("/view-all")
    @Operation(summary = "모든 키워드 조회 API", description = "모든 키워드를 ID로 조회합니다.")
    public ResponseEntity<List<KeywordResponseDto>> findAllKeywords() {
        List<KeywordResponseDto> keywords = keywordService.findAllKeywords();
        return ResponseEntity.ok(keywords);
    }


}
