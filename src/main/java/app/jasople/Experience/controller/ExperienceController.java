package app.jasople.Experience.controller;
import app.jasople.Config.ApiResponse;
import app.jasople.Experience.dto.ExperienceResponseDto;
import app.jasople.Experience.dto.ExperienceSaveRequestDto;
import app.jasople.Experience.service.ExperienceService;
import app.jasople.security.CustomUserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ApiResponse<ExperienceResponseDto> saveExperience(@RequestBody ExperienceSaveRequestDto requestDto, @AuthenticationPrincipal CustomUserDetail userDetail) {
        return ApiResponse.onSuccess(experienceService.save(requestDto,userDetail.getUser()));
    }

    @Operation(summary = "경험시트 조회 API", description = "경험시트를 ID로 조회합니다.")
    @GetMapping("/view/{exId}")
    public ApiResponse<ExperienceResponseDto> viewExperience(@PathVariable Long exId, @AuthenticationPrincipal CustomUserDetail userDetail) {
        ExperienceResponseDto experience = experienceService.findById(exId,userDetail.getUser());
        return ApiResponse.onSuccess(experience);
    }

    @Operation(summary = "경험시트 리스트 조회 API", description = "모든 경험시트 리스트를 조회합니다.")
    @GetMapping("/view/all")
    public ApiResponse<List<ExperienceResponseDto>> viewAll(@AuthenticationPrincipal CustomUserDetail userDetail) {
        List<ExperienceResponseDto> experiences = experienceService.findList(userDetail.getUser());
        return ApiResponse.onSuccess(experiences);
    }

    // 경험시트 삭제
//    @Operation(summary = "경험시트 삭제 API", description = "모든 경험시트 리스트를 조회합니다.")
//    @DeleteMapping("/delete")
//    public ApiResponse<String> delete(@AuthenticationPrincipal CustomUserDetail userDetail, @PathVariable Long id){
//        experienceService.delete(userDetail, id);
//    }

}
