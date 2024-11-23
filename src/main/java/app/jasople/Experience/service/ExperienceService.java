package app.jasople.Experience.service;

import app.jasople.Category.entity.Category;
import app.jasople.Category.entity.CategoryRepository;
import app.jasople.Config.ApiResponse;
import app.jasople.Config.Exception.handler.ExperienceHandler;
import app.jasople.Experience.converter.ExKeywordConverter;
import app.jasople.Experience.converter.ExperienceConverter;
import app.jasople.Experience.dto.ExseetUpdateDto;
import app.jasople.Keywords.entity.ExKeywordsRepository;
import app.jasople.Experience.dto.ExperienceResponseDto;
import app.jasople.Experience.dto.ExperienceSaveRequestDto;
import app.jasople.Experience.entity.Experience;
import app.jasople.Experience.entity.ExperienceRepository;
import app.jasople.Keywords.entity.ExperienceKeywords;
import app.jasople.Keywords.entity.Keywords;
import app.jasople.Keywords.entity.KeywordsRepository;
import app.jasople.User.entity.User;
import app.jasople.security.CustomUserDetail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final CategoryRepository categoryRepository;

    private final ExperienceRepository experienceRepository;

    private final ExKeywordsRepository exKeywordsRepository;

    private final KeywordsRepository keywordsRepository;

    @Transactional
    public ExperienceResponseDto save(ExperienceSaveRequestDto requestDto, User user) {
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Experience savedExperience = experienceRepository.save(requestDto.ToEntity(category,user));

        // 키워드 ID 리스트를 통해 Keyword 엔티티를 찾고 ExperienceKeyword 저장
        List<Long> keywordList = requestDto.getKeywordList();
        for (Long keywordId : keywordList) {
            Keywords keyword = keywordsRepository.findById(keywordId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid keyword ID: " + keywordId));

            ExperienceKeywords experienceKeyword = new ExperienceKeywords(savedExperience, keyword);
            exKeywordsRepository.save(experienceKeyword);
        }

        // 키워드 객체 리스트 가져오기
        List<Keywords> keywords = keywordsRepository.findAllById(requestDto.getKeywordList());


        return new ExperienceResponseDto(savedExperience,keywords);
    }
    @Transactional
    public ExperienceResponseDto findById(Long id, User user) {

        // 경험 엔티티 찾기
        Experience experience = experienceRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다."));

        // 연결된 키워드 리스트 가져오기
        List<Keywords> keywords = exKeywordsRepository.findByExperience(experience)
                .stream()
                .map(ExperienceKeywords::getKeyword)
                .collect(Collectors.toList());

        // DTO 생성
        return ExperienceResponseDto.builder()
                .experience(experience)
                .keywords(keywords)
                .build();
    }

    @Transactional
    public List<ExperienceResponseDto> findList(User user) {
        // 사용자가 작성한 모든 경험 엔티티 찾기
        List<Experience> experiences = experienceRepository.findByUser(user);

        return experiences.stream()
                .map(experience -> {
                    // 연결된 키워드 리스트 가져오기
                    List<Keywords> keywords = exKeywordsRepository.findByExperience(experience)
                            .stream()
                            .map(ExperienceKeywords::getKeyword)
                            .collect(Collectors.toList());

                    // ExperienceResponseDto 생성
                    return ExperienceResponseDto.builder()
                            .experience(experience)
                            .keywords(keywords)
                            .build();
                })
                .collect(Collectors.toList());
    }


    @Transactional
    public void delete(CustomUserDetail userDetail, Long id) {

        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 id입니다."));

        int i = exKeywordsRepository.deleteByExperience(experience);

        experienceRepository.delete(experience);

    }

    @Transactional
    public void update(CustomUserDetail userDetail, ExseetUpdateDto dto) {

        //경험시트 조회
        Experience ex = experienceRepository.findById(dto.getExperienceId()).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 경험시트입니다."));

        //키워드 삭제 후 재등록
        exKeywordsRepository.deleteAllByExperience(ex);
        //키워드 등록
        long count = dto.getKeywordList().stream()
                        .map(id -> exKeywordsRepository.save(
                                ExKeywordConverter.toEntity(ex,
                                        keywordsRepository.findById(id).orElseThrow(()
                                        -> new IllegalArgumentException("유효하지 않은 경험시트입니다."))))
                            )
                        .count();

        Experience updatedExperience = ExperienceConverter.toEntity(dto); // DTO를 엔티티로 변환
        experienceRepository.save(updatedExperience);

    }
}
