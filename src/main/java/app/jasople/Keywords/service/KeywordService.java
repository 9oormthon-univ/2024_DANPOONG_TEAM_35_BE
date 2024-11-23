package app.jasople.Keywords.service;

import app.jasople.Keywords.dto.KeywordResponseDto;
import app.jasople.Keywords.entity.Keywords;
import app.jasople.Keywords.entity.KeywordsRepository;
import app.jasople.Keywords.entity.keywordType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordsRepository keywordsRepository;


    // 공통 메서드로 KeywordType에 따라 키워드 조회
    public List<KeywordResponseDto> findKeywordsByType(keywordType keywordType) {
        List<Keywords> keywords = keywordsRepository.findByKeywordType(keywordType);

        return keywords.stream()
                .map(KeywordResponseDto::new)
                .collect(Collectors.toList());
    }

}

