package app.jasople.Experience.service;

import app.jasople.Category.entity.Category;
import app.jasople.Category.entity.CategoryRepository;
import app.jasople.Keywords.entity.ExKeywordsRepository;
import app.jasople.Experience.dto.ExperienceResponseDto;
import app.jasople.Experience.dto.ExperienceSaveRequestDto;
import app.jasople.Experience.entity.Experience;
import app.jasople.Experience.entity.ExperienceRepository;
import app.jasople.Keywords.entity.ExperienceKeywords;
import app.jasople.Keywords.entity.Keywords;
import app.jasople.Keywords.entity.KeywordsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final CategoryRepository categoryRepository;

    private final ExperienceRepository experienceRepository;

    private final ExKeywordsRepository exKeywordsRepository;

    private final KeywordsRepository keywordsRepository;

    @Transactional
    public ExperienceResponseDto save(ExperienceSaveRequestDto requestDto) {
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Experience savedExperience = experienceRepository.save(requestDto.ToEntity(category));

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
    public ExperienceResponseDto findById(Long id) {
        // 경험 엔티티 찾기
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid experience ID"));

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
    public List<ExperienceResponseDto> findList() {
        // 모든 경험 엔티티 찾기
        List<Experience> experiences = experienceRepository.findAll();

        // 각 경험에 대해 ExperienceResponseDto 생성
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


    // 추후 구현
    // 경험 삭제
    // 경험 수정

}