package app.jasople.Job.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobInitializer implements ApplicationRunner {

    private final JobRepository jobRepository;

    @Autowired
    public JobInitializer(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // 직무명 리스트
        List<String> jobNames = List.of(
                "경영기획", "사업기획", "경영전략", "사업전략", "경영분석·컨설턴트",
                "인사", "노무", "교육", "채용", "급여", "보상관리",
                "재무", "세무", "IR", "자금",
                "일반사무", "총무", "비서", "사무보조",
                "브랜드마케팅", "콘텐츠마케팅", "퍼포먼스·온라인마케팅", "마케팅전략·기획", "상품기획",
                "광고기획·AE", "광고제작·카피", "언론홍보·PR", "사내홍보",
                "물류관리·기획", "유통관리·기획", "재고",
                "해외영업", "무역·수출입관리",
                "구매", "자재",
                "iOS개발", "안드로이드개발",
                "프론트엔드개발", "서버·백엔드개발", "HTML·퍼블리싱",
                "데이터엔지니어", "데이터분석", "DBA",
                "생산관리", "공정관리", "품질관리",
                "안전관리", "환경관리",
                "자동차", "기계",
                "화학", "에너지", "환경", "화장품",
                "바이오·제약", "식품",
                "광고 디자인", "그래픽디자인·CG", "출판·편집디자인", "캐릭터·애니메이션"
        );

        // 직무명 리스트를 사용하여 중복을 피해서 Job 엔티티를 생성하고 저장
        jobNames.stream()
                .distinct() // 중복 직무명을 필터링
                .forEach(name -> {
                    // 직무명이 존재하는지 확인
                    if (!jobRepository.existsByName(name)) {
                        Job job = new Job(name);
                        jobRepository.save(job);
                    }
                });
    }
}
