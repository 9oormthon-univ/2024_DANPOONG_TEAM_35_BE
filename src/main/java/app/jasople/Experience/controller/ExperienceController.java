package app.jasople.Experience.controller;
import app.jasople.Experience.dto.ExperienceResponseDto;
import app.jasople.Experience.dto.ExperienceSaveRequestDto;
import app.jasople.Experience.entity.Experience;
import app.jasople.Experience.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
@Tag(name = "경험시트 api", description = "경험 시트 Api입니다.")
public class ExperienceController {

    private final ExperienceService experienceService;

    @Operation(summary = "경험시트 생성 API", description = "경험시트를 생성합니다.")
    @PostMapping("/write")
    public ResponseEntity<ExperienceResponseDto> saveExperience(@RequestBody ExperienceSaveRequestDto requestDto) {
        ExperienceResponseDto savedExperience = experienceService.save(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExperience);
    }

    @Operation(summary = "경험시트 조회 API", description = "경험시트를 ID로 조회합니다.")
    @GetMapping("/view/{exId}")
    public ResponseEntity<ExperienceResponseDto> viewExperience(@PathVariable Long exId) {
        ExperienceResponseDto experience = experienceService.findById(exId);
        return ResponseEntity.ok(experience);
    }

    @Operation(summary = "경험시트 리스트 조회 API", description = "모든 경험시트 리스트를 조회합니다.")
    @GetMapping("/view/all")
    public ResponseEntity<List<ExperienceResponseDto>> viewAll() {
        List<ExperienceResponseDto> experiences = experienceService.findList();
        return ResponseEntity.ok(experiences);
    }

    // 경험 삭제

    // 경험 조회

    // 경험 목록 조회
}
