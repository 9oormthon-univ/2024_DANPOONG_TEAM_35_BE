package app.jasople.IndustryInfo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class IndustryInfoInitializer implements ApplicationRunner {

    private final IndustryInfoRepository industryInfoRepository;

    @Autowired
    public IndustryInfoInitializer(IndustryInfoRepository industryInfoRepository) {
        this.industryInfoRepository = industryInfoRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // 업계 소식 정보 생성 및 저장
        saveIndustryInfo("효과적인 웹사이트 구축을 위한 개발방법론에 관한 연구",
                "웹사이트 개발의 복잡성과 대형화에 따라 프로젝트 관리 기법과 개발 방법론의 필요성을 강조하며, 국내 여건에 맞는 웹사이트 개발 방법론의 필요성과 타당성을 분석한 연구입니다.",
                "논문");
        saveIndustryInfo("웹 3.0의 재부상: 이슈 및 전망",
                "웹 3.0의 부상과 관련된 이슈와 전망을 다루며, 웹 산업의 새로운 패러다임과 기술적 흐름을 분석한 연구입니다.",
                "논문");
        saveIndustryInfo("2024년 상위 10가지 웹 개발 트렌드",
                "디지털 산업의 빠른 발전에 따라 주목해야 할 2024년 웹 개발 트렌드 10가지를 소개하는 기사입니다. Jamstack, 프로그레시브 웹 앱(PWA), 서버리스 아키텍처 등의 최신 동향을 다룹니다.",
                "기사");
        saveIndustryInfo("2024년 웹 개발 트렌드로 앞서 나가세요",
                "사용자 경험 혁신과 경쟁 우위 유지를 위한 최신 웹 개발 동향을 소개하며, 생성형 AI 도구, PWA 도입 확산, 서버리스 아키텍처 활용 증가 등을 다룹니다.",
                "기사");
        saveIndustryInfo("웹 프론트엔드 프레임워크 및 라이브러리 장단점 연구",
                "최근 웹 어플리케이션 환경의 빠른 발전에 따라 등장한 다양한 프론트엔드 프레임워크와 라이브러리의 장단점을 비교 분석한 연구입니다.",
                "논문");
        saveIndustryInfo("웹 기반 시스템의 개발 프로세스에 관한 연구 및 웹 서버 구축",
                "웹 어플리케이션 개발 시 적용될 웹 공학에 대해 소프트웨어 공학과의 차이점 및 프로세스 모델을 웹 기반에서 적용한 방법을 연구한 논문입니다.",
                "논문");
        saveIndustryInfo("웹사이트 개발 방법론에 관한 연구",
                "국내 기업의 웹사이트 개발 시 문제되는 현안을 파악하고, 현업에서 사용하는 다양한 웹사이트 개발 방법론을 수집하여 비교한 연구입니다.",
                "논문");
        saveIndustryInfo("웹 2.0 기술 현황 및 전망",
                "구글, 아마존 등의 성공과 함께 웹 2.0으로 대표되는 실용적 웹 응용 동향과 그 핵심 기술 흐름을 살펴본 연구입니다.",
                "논문");
        saveIndustryInfo("심층 웹 문서 자동 수집을 위한 크롤링 알고리즘 설계 및 실험",
                "심층 웹의 정보를 자동으로 수집하기 위한 크롤링 알고리즘을 설계하고 실험한 연구입니다.",
                "논문");
        saveIndustryInfo("2024년 웹 개발 트렌드 10가지, 핵심만 딱 정리함",
                "2024년에 주목해야 할 최신 웹 개발 트렌드 10가지를 소개하는 기사입니다. 반응형 웹 디자인, PWA, 서버리스 아키텍처 등 다양한 기술을 다룹니다.",
                "기사");
    }

    private void saveIndustryInfo(String title, String content, String category) {
        if (!industryInfoRepository.existsByTitle(title)) {
            industryInfoRepository.save(new IndustryInfo(title, content, category));
        }
    }
}