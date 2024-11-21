package app.jasople.IndustryInfo.service;

import app.jasople.IndustryInfo.dto.IndustryInfoResponseDto;
import app.jasople.IndustryInfo.dto.IndustryInfoScrapDto;
import app.jasople.IndustryInfo.entity.IndustryInfo;
import app.jasople.IndustryInfo.entity.IndustryInfoRepository;
import app.jasople.IndustryInfo.entity.ScrapedInfo;
import app.jasople.IndustryInfo.entity.ScrapedInfoRepository;
import app.jasople.Keywords.entity.InfoKeywords;
import app.jasople.User.entity.User;
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



}
