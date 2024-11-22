//package app.jasople.Config.oauth.provider;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//
//@Service
//@RequiredArgsConstructor
//public class KakaoAuthService {
//
//    private final RestTemplate restTemplate;
//
//    public String getAccessToken(String code) {
//        String tokenUrl = "https://kauth.kakao.com/oauth/token";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("grant_type", "authorization_code");
//        body.add("client_id", "your_client_id");
//        body.add("redirect_uri", "http://localhost:8080/api/auth/kakao/callback");
//        body.add("code", code);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
//
//        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
//        Map<String, Object> responseBody = response.getBody();
//
//        return responseBody.get("access_token").toString();
//    }
//}