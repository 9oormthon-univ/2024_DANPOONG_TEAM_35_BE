package app.jasople.IndustryInfo.cotroller;

import app.jasople.Config.ApiResponse;
import app.jasople.IndustryInfo.dto.IndustryInfoResponseDto;
import app.jasople.IndustryInfo.dto.IndustryInfoScrapDto;
import app.jasople.IndustryInfo.dto.IndustryInfoScrapResponseDto;
import app.jasople.IndustryInfo.service.IndustryInfoService;
import app.jasople.security.CustomUserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/industry-info")
@Tag(name = "업계소식 API", description = "업계소식 관련 API입니다.")
public class IndustryInfoController {

    private final IndustryInfoService industryInfoService;


    @Operation(summary = "업계소식 조회 API", description = "업계 소식을 ID로 조회합니다.")
    @GetMapping("/view/{id}")
    public ApiResponse<IndustryInfoResponseDto> viewIndustryInfo(@PathVariable Long id) {
        IndustryInfoResponseDto industryInfo = industryInfoService.findById(id);
        return ApiResponse.onSuccess(industryInfo);
    }

    @Operation(summary = "업계소식 리스트 조회 API", description = "업계 소식 리스트를 조회합니다.")
    @GetMapping("/view/all")
    public ApiResponse<List<IndustryInfoResponseDto>> viewAllIndustryInfo() {
        List<IndustryInfoResponseDto> industryInfos = industryInfoService.findList();
        return ApiResponse.onSuccess(industryInfos);
    }

    @Operation(summary = "업계소식 스크랩 API", description = "업계 소식을 스크랩합니다.")
    @PostMapping("/scrap")
    public ApiResponse<IndustryInfoResponseDto> scrapIndustryInfo(@RequestBody IndustryInfoScrapDto requestDto, @AuthenticationPrincipal CustomUserDetail userDetail) {
        IndustryInfoResponseDto industryInfo = industryInfoService.scrapInfo(requestDto, userDetail.getUser());
        return ApiResponse.onSuccess(industryInfo);
    }


    @Operation(summary = "스크랩한 업계소식 조회 API", description = "사용자가 스크랩한 모든 업계소식을 조회합니다.")
    @GetMapping("/view/scrap/{userId}")
    public ApiResponse<List<IndustryInfoScrapResponseDto>> viewScrapedIndustryInfo(@PathVariable Long userId, @AuthenticationPrincipal CustomUserDetail userDetail) {
        List<IndustryInfoScrapResponseDto> scrapedIndustryInfos = industryInfoService.findScrapedByUser(userDetail.getUser());
        return ApiResponse.onSuccess(scrapedIndustryInfos);
    }

    @Operation(summary = "업계 소식 검색 API", description = "업계 소식 리스트에서 검색합니다.")
    @GetMapping("/search")
    public ApiResponse<List<IndustryInfoResponseDto>> searchIndustryInfo(@RequestParam String keyword) {
        List<IndustryInfoResponseDto> searchResults = industryInfoService.searchByKeyword(keyword);
        return ApiResponse.onSuccess(searchResults);
    }

}

