package app.jasople.Keywords.service;

import app.jasople.Keywords.dto.KeywordFilterResponseDto;
import app.jasople.Keywords.dto.KeywordResponseDto;
import app.jasople.Keywords.entity.*;
import app.jasople.User.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordsRepository keywordsRepository;

    private final ExKeywordsRepository exKeywordsRepository;

    private final InfoKeywordsRepository infoKeywordsRepository;


    // 공통 메서드로 KeywordType에 따라 키워드 조회
    public List<KeywordResponseDto> findKeywordsByType(keywordType keywordType) {
        List<Keywords> keywords = keywordsRepository.findByKeywordType(keywordType);

        return keywords.stream()
                .map(KeywordResponseDto::new)
                .collect(Collectors.toList());
    }

    // 경험시트 키워드 집계
    public List<KeywordFilterResponseDto> filterKeywordsInEx(User user) {
        // 경험시트에서 사용자별 키워드 조회
        List<Keywords> keywords = exKeywordsRepository.findByUser(user);

        // 키워드 이름으로 그룹화하고, 각 키워드의 개수를 집계
        Map<String, Long> keywordCountMap = keywords.stream()
                .collect(Collectors.groupingBy(Keywords::getName, Collectors.counting()));

        // 전체 키워드 개수 계산
        long totalKeywordsCount = keywords.size();

        // 결과 리스트 생성
        List<KeywordFilterResponseDto> result = new ArrayList<>();

        // 첫 번째 요소로 전체 개수 추가
        result.add(new KeywordFilterResponseDto("전체", (int) totalKeywordsCount));

        // 개별 키워드를 집계한 결과를 리스트에 추가
        result.addAll(keywordCountMap.entrySet().stream()
                .map(entry -> new KeywordFilterResponseDto(entry.getKey(), Math.toIntExact(entry.getValue())))
                .collect(Collectors.toList()));

        return result;
    }

    public List<KeywordFilterResponseDto> filterKeywordsInInfo(User user){
        // 경험시트에서 사용자별 키워드 조회
        List<Keywords> keywords = infoKeywordsRepository.findByUser(user);

        // 키워드 이름으로 그룹화하고, 각 키워드 개수 집계
        Map<String, Long> keywordCountMap = keywords.stream()
                .collect(Collectors.groupingBy(Keywords::getName, Collectors.counting()));

        // 전체 키워드 개수 계산
        long totalKeywordsCount = keywords.size();

        // 결과 리스트 생성
        List<KeywordFilterResponseDto> result = new ArrayList<>();

        // 첫 번째 요소로 전체 개수 추가
        result.add(new KeywordFilterResponseDto("전체", (int) totalKeywordsCount));

        // 개별 키워드를 집계한 결과를 리스트에 추가
        result.addAll(keywordCountMap.entrySet().stream()
                .map(entry -> new KeywordFilterResponseDto(entry.getKey(), Math.toIntExact(entry.getValue())))
                .collect(Collectors.toList()));

        return result;
    }





}

