package app.jasople.Essay.controller;


import app.jasople.Config.ApiResponse;
import app.jasople.Essay.dto.*;
import app.jasople.Essay.service.Impl.gptServiceImpl;
import app.jasople.User.entity.User;
import app.jasople.security.CustomUserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/essay")
@RequiredArgsConstructor
@Tag(name = "자소서 API", description = "자소서 관련 기능 API입니다.")
public class EssayController {

    private final gptServiceImpl gptService;


    @Operation(summary = "관심 직무 및 회사 등록", description = "사용자의 관심 직무 및 회사를 등록합니다.")
    @PostMapping("/register-job-company")
    public ApiResponse<String> registerJob(@RequestBody EssayInfoRequestDto requestDto, @AuthenticationPrincipal CustomUserDetail userDetail) {
        User user = userDetail.getUser();
        gptService.registerJob(requestDto, user);
        return ApiResponse.onSuccess("관심 직무 및 회사가 성공적으로 등록되었습니다.");
    }

    @Operation(summary = "직무 리스트 조회", description = "모든 직무 리스트를 조회합니다.")
    @GetMapping("/jobs")
    public ApiResponse<List<JobResponseDto>> getJobList() {
        List<JobResponseDto> jobList = gptService.getList();
        return ApiResponse.onSuccess(jobList);
    }

    @Operation(summary = "자소서 작성", description = "사용자가 입력한 정보를 바탕으로 자소서를 작성하고 저장합니다.")
    @PostMapping("/write")
    public ApiResponse<List<EssayResponseDto>> getEssay(@RequestBody EssayWriteRequestDto requestDto, @AuthenticationPrincipal CustomUserDetail userDetail) {
        User user = userDetail.getUser();
        List<EssayResponseDto> essays = gptService.getEssay(requestDto, user);
        return ApiResponse.onSuccess(essays);
    }

    @Operation(summary = "자소서 리스트 조회", description = "사용자가 작성한 모든 자소서 리스트를 조회합니다.")
    @GetMapping("/list")
    public ApiResponse<List<EssayViewResponseDto>> getEssayList(@AuthenticationPrincipal CustomUserDetail userDetail) {
        User user = userDetail.getUser();
        List<EssayViewResponseDto> essayList = gptService.getEssayList(user);
        return ApiResponse.onSuccess(essayList);
    }


    @Operation(summary = "자소서 개별 조회", description = "자소서를 ID로 조회합니다.")
    @GetMapping("/view/{id}")
    public ApiResponse<EssayViewResponseDto> getEssayById(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetail userDetail) {
        User user = userDetail.getUser();
        EssayViewResponseDto essay = gptService.getEssayById(id, user);
        return ApiResponse.onSuccess(essay);
    }


    @Operation(summary = "직무, 회사 조회", description = "입력한 직무, 회사를 조회합니다.")
    @GetMapping("/view/job-company}")
    public ApiResponse<EssayInfoResponseDto> getEssayInfo(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetail userDetail) {
        User user = userDetail.getUser();
        EssayInfoResponseDto essayInfo = gptService.getJobCompanyInfo(user);
        return ApiResponse.onSuccess(essayInfo);
    }


}