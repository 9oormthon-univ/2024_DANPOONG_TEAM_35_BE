package app.jasople.Essay.service.Impl;

import app.jasople.Category.entity.Category;
import app.jasople.Essay.dto.*;
import app.jasople.Essay.entity.Essay;
import app.jasople.Essay.entity.EssayRepository;
import app.jasople.Essay.service.gptService;
import app.jasople.Experience.entity.ExperienceRepository;
import app.jasople.IndustryInfo.entity.IndustryInfoRepository;
import app.jasople.Job.entity.InterestedJob;
import app.jasople.Job.entity.InterestedJobRepository;
import app.jasople.Job.entity.Job;
import app.jasople.Job.entity.JobRepository;
import app.jasople.Keywords.entity.KeywordsRepository;
import app.jasople.User.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE essay SET deleted = true WHERE id = ?")
public class gptServiceImpl implements gptService {
    @Value("${openai.api.key}")
    private String apikey;

    private final ExperienceRepository experienceRepository;
    private final IndustryInfoRepository industryInfoRepository;
    private final KeywordsRepository keywordsRepository;

    private final JobRepository jobRepository;

    private final InterestedJobRepository interestedJobRepository;

    private final EssayRepository essayRepository;

    // GPT 응답
    public JsonNode callChatGpt(String prompt) throws JsonProcessingException {
        final String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apikey);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("model", "gpt-4-0613");

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> assistantMessage = new HashMap<>();
        assistantMessage.put("role", "system");
        // 경험시트 카테고리 고려 추가하기
        assistantMessage.put("content", "당신은 사용자가 요청한 정보를 기반으로 자기소개서를 작성하는 역할을 합니다. 사용자가 제공한 경험, 업계 소식, 키워드를 사용하여 각 문항에 맞는 답변을 생성해주세요.다음과 같은 규칙을 지켜주세요. 1. 글자수를 지켜줘. \n" +
                "2. 성과와 결과 제시: 자기소개서 구조에 먼저 집중해야해. 앞에 '성과'와 '결과'부터 제시하고, 그 '성과'와 '결과'를 만들어냈던 나의 '액션'과 '상황'을 서술시간 혹은 분량이 부족하다면 '성과'+결과를 이끌어냈던 나의 '액션' 작성해줘.\n" +
                "3. 두괄식으로 작성하는 것이 중요하기 때문에 소제목을 무조건 작성해줘.\n" +
                "4. 사용하지 말아야 할 표현들을 제외해줘: 줄임말보다는 본말을 사용하도록 해주고, 타고났다', '운이 좋아서 다행'이라는 표현은 지양약점을 밝히지 말도록 해줘. 무작정 '열심히 배우겠다'는 자세는 지양해줘.");
        messages.add(assistantMessage);

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
        messages.add(userMessage);

        bodyMap.put("messages", messages);

        String body = objectMapper.writeValueAsString(bodyMap);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        return objectMapper.readTree(response.getBody());
    }

    // 경험시트, 업계소식에서 프롬프트 생성
    public String generatePromptForQuestion(int questionNumber, List<Long> experienceIds, List<Long> industryInfoIds, User user) {
        // 경험 정보 생성
        String experiences = experienceIds == null || experienceIds.isEmpty() ? "경험 정보 없음" : experienceIds.stream()
                .map(id -> experienceRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 경험 ID입니다: " + id)))
                .map(experience -> String.format("경험제목: %s, 문제 상황: %s, 해결 방법: %s, 결과: %s, 카테고리: %s, 기간: %s ~ %s",
                        experience.getTitle(), experience.getBackground(), experience.getSolution(), experience.getResult(),
                        experience.getCategory(), experience.getStartDate(), experience.getEndDate()))
                .collect(Collectors.joining("\n"));

        // 업계 소식 정보 생성
        String industryInfos = industryInfoIds == null || industryInfoIds.isEmpty() ? "업계 소식 정보 없음" : industryInfoIds.stream()
                .map(id -> industryInfoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 업계 소식 ID입니다: " + id)))
                .map(info -> String.format("업계 소식: %s, 내용: %s", info.getTitle(), info.getContent()))
                .collect(Collectors.joining("\n"));

        // 키워드 정보 생성
        String keywords = keywordsRepository.findAll().isEmpty() ? "키워드 정보 없음" : keywordsRepository.findAll().stream()
                .map(keyword -> "키워드: " + keyword.getInfoKeywords())
                .collect(Collectors.joining(", "));

        // 관심 직무 정보 생성
        InterestedJob interestedJob = interestedJobRepository.findByUser(user);
        String jobInfoText = interestedJob == null ? "관심 직무 정보 없음" : String.format("관심 직무는 %s 입니다. 지원하고자 하는 회사는 %s 입니다.", interestedJob.getJob().getName(), interestedJob.getCompany());

        // 문항에 따른 프롬프트 생성
        String questionPrompt = "";
        switch (questionNumber) {
            case 1:
                questionPrompt = "지원동기 및 포부: 지원동기와 입사 후 회사에서 이루고 싶은 꿈을 기술하십시오. 300자 이내로 작성하되, 최소 70%는 넘겨야 합니다.";
                break;
            case 2:
                questionPrompt = "직무 역량: 해당 직무 분야에 지원하게 된 이유와 선택 직무에 본인이 적합하다고 판단할 수 있는 이유 및 근거를 제시해주십시오. 600자 이내로 작성하되, 최소 70%는 넘겨야 합니다.";
                break;
            case 3:
                questionPrompt = "도전(성공/실패) : 학업 외 가장 열정적이고 도전적으로 몰입하여 성과를 창출했거나 목표를 달성한 경험을 기술하십시오. 600자 이내로 작성하되, 최소 70%는 넘겨야 합니다.";
                break;
            case 4:
                questionPrompt = "남들과 다른 새로운 관점으로 변화/혁신을 추구한 경험과 그를 통해 배운 점이 무엇인지 기술하십시오. 600자 이내로 작성하되, 최소 70%는 넘겨야 합니다.";
                break;
            case 5:
                questionPrompt = "창의성(문제해결능력) : 공동의 목표를 달성하기 위해 타인과 협업했던 경험과 그 과정에서 본인이 수행한 역할, 그리고 그 해당 경험을 통해 얻은 것은 무엇인지 구체적으로 기술해 주십시오. 400자 이내로 작성하되, 최소 70%는 넘겨야 합니다.";
                break;
            default:
                throw new IllegalArgumentException("유효하지 않은 문항 번호입니다: " + questionNumber);
        }

        // 최종 프롬프트 생성
        return String.format("%s\n%s\n경험: %s\n업계 소식: %s\n키워드: %s\n이 정보를 바탕으로 문항을 작성해주세요.",
                questionPrompt, jobInfoText, experiences, industryInfos, keywords);
    }


    // 관심 직무 정보, 회사를 가져와서 텍스트로 변경
    public String getJobInfoText(User user) {
        InterestedJob interestedJob = interestedJobRepository.findByUser(user);
        String job = interestedJob.getJob().getName();
        String company = interestedJob.getCompany();
        return String.format("관심 직무는 %s 입니다. 지원하고자 하는 회사는 %s 입니다.", job, company);
    }


    // 직무, 회사 등록
    public void registerJob(EssayInfoRequestDto requestDto, User user){
        Job job = jobRepository.findById(requestDto.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 직무 id입니다."));
        InterestedJob interestedJob = interestedJobRepository.save(requestDto.toEntity(user,job));
    }

    // 직무 리스트 반환
    @Transactional
    public List<JobResponseDto> getList() {
        return jobRepository.findAll().stream()
                .map(job -> JobResponseDto.builder()
                        .id(job.getId())
                        .name(job.getName())
                        .build())
                .collect(Collectors.toList());
    }


    // 최종적으로 자소서 등록
    @Override
    @Transactional
    public List<EssayResponseDto> getEssay(EssayWriteRequestDto requestDto, User user) {
        List<EssayResponseDto> responseDtos = new ArrayList<>();
        StringBuilder combinedContent = new StringBuilder();

        for (EssayWriteRequestDto.EssayWriteItemDto item : requestDto.getEssayWriteItems()) {
            try {
                String prompt = generatePromptForQuestion(item.getNumber(), item.getExperienceList(), item.getInfoList(), user);
                JsonNode jsonNode = callChatGpt(prompt);
                String content = jsonNode.path("choices").get(0).path("message").path("content").asText();

                combinedContent.append(content).append(" ");
                responseDtos.add(new EssayResponseDto(item.getNumber(), content));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        EssaySaveRequestDto saveRequestDto = new EssaySaveRequestDto(requestDto.getTitle(),combinedContent.toString().trim());
        essayRepository.save(saveRequestDto.toEntity(user));
        return responseDtos;
    }

    // 자소서 리스트 조회
    @Transactional
    public List<EssayViewResponseDto> getEssayList(User user) {
        List<Essay> essays = essayRepository.findByUser(user);
        return essays.stream()
                .map(essay -> new EssayViewResponseDto(essay))
                .collect(Collectors.toList());
    }

    // 자소서 개별 조회
    @Transactional
    public EssayViewResponseDto getEssayById(Long id, User user) {
        Essay essay = (Essay) essayRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 자소서 ID이거나 해당 사용자에게 권한이 없습니다."));
        return new EssayViewResponseDto(essay);
    }

    // 직무, 회사 조회
    @Transactional
    public EssayInfoResponseDto getJobCompanyInfo(User user) {
        InterestedJob interestedJob = interestedJobRepository.findByUser(user);
        return new EssayInfoResponseDto(interestedJob);
    }

    // 자소서 수정
    @Transactional
    public void updateEssayById(Long id, User user, EssayUpdateRequestDto requestDto) {
        Essay essay = essayRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("유효하지 않은 자소서 ID이거나 해당 사용자에게 권한이 없습니다."));
        essay.update(requestDto.getTitle(), requestDto.getContent());

    }

    // 자소서 삭제
    @Transactional
    public void deleteEssayById(Long id){
        Essay essay = essayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 자소서 ID이거나 해당 사용자에게 권한이 없습니다."));
        essayRepository.delete(essay);

    }


}
