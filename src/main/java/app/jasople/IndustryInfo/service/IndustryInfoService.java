package app.jasople.IndustryInfo.service;

import app.jasople.IndustryInfo.dto.IndustryInfoResponseDto;
import app.jasople.IndustryInfo.dto.IndustryInfoScrapDto;
import app.jasople.IndustryInfo.dto.IndustryInfoScrapResponseDto;
import app.jasople.IndustryInfo.entity.IndustryInfo;
import app.jasople.IndustryInfo.entity.IndustryInfoRepository;
import app.jasople.IndustryInfo.entity.ScrapedInfo;
import app.jasople.IndustryInfo.entity.ScrapedInfoRepository;
import app.jasople.Keywords.entity.InfoKeywords;

import app.jasople.Keywords.entity.InfoKeywordsRepository;
import app.jasople.Keywords.entity.Keywords;
import app.jasople.User.entity.User;
import app.jasople.User.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndustryInfoService {

    private final IndustryInfoRepository industryInfoRepository;

    private final ScrapedInfoRepository scrapedInfoRepository;

    private final UserRepository userRepository;

    private final InfoKeywordsRepository infoKeywordsRepository;

    // 개별 조회
    @Transactional
    public IndustryInfoResponseDto findById(Long id) {
        IndustryInfo industryInfo = industryInfoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid IndustryInfo ID: " + id));
        return new IndustryInfoResponseDto(industryInfo);
    }

    // 목록 조회
    @Transactional
    public List<IndustryInfoResponseDto> findList() {
        List<IndustryInfo> industryInfoList = industryInfoRepository.findAll();
        return industryInfoList.stream()
                .map(IndustryInfoResponseDto::new)
                .collect(Collectors.toList());
    }

    // 스크랩 생성
    @Transactional
    public IndustryInfoResponseDto scrapInfo(IndustryInfoScrapDto requestDto, User user) {
        IndustryInfo industryInfo = industryInfoRepository.findById(requestDto.getIndustryInfoId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 id입니다 : " + requestDto.getIndustryInfoId()));

        List<InfoKeywords> keywords = infoKeywordsRepository.findAllById(requestDto.getKeywordIds());

        ScrapedInfo scrap = requestDto.toEntity(industryInfo, keywords, user);
        scrapedInfoRepository.save(scrap);
        return new IndustryInfoResponseDto(industryInfo);
    }

    // 스크랩한 항목 조회
    @Transactional
    public List<IndustryInfoScrapResponseDto> findScrapedByUser(User user) {
        // 유저의 스크랩한 항목 리스트 가져오기
        List<ScrapedInfo> scrapedInfoList = scrapedInfoRepository.findByUser(user);

        return scrapedInfoList.stream()
                .map(scrap -> {
                    // 각 스크랩에 연결된 키워드 리스트 가져오기
                    List<Keywords> keywords = infoKeywordsRepository.findByScrapedInfo(scrap)
                            .stream()
                            .map(InfoKeywords::getKeyword)
                            .collect(Collectors.toList());

                    // IndustryInfoScrapResponseDto 생성
                    return new IndustryInfoScrapResponseDto(scrap, keywords);
                })
                .collect(Collectors.toList());
    }


    // 목록에서 검색 기능
    @Transactional
    public List<IndustryInfoResponseDto> searchByKeyword(String keyword) {
        List<IndustryInfo> searchResults = industryInfoRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        return searchResults.stream()
                .map(IndustryInfoResponseDto::new)
                .collect(Collectors.toList());
    }


}
