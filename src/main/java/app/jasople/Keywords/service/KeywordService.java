package app.jasople.Keywords.service;

import app.jasople.Keywords.dto.KeywordResponseDto;
import app.jasople.Keywords.entity.Keywords;
import app.jasople.Keywords.entity.KeywordsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final KeywordsRepository keywordsRepository;

    @Transactional
    public List<KeywordResponseDto> findAllKeywords() {
        List<Keywords> keywords = keywordsRepository.findAll();

        // Keywords 엔티티 리스트를 KeywordResponseDto 리스트로 변환
        return keywords.stream()
                .map(KeywordResponseDto::new) // 수정된 생성자를 이용하여 간단하게 변환
                .collect(Collectors.toList());
    }
}

