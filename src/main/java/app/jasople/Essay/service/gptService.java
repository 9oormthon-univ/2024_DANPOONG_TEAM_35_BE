package app.jasople.Essay.service;

import app.jasople.Essay.dto.EssayResponseDto;
import app.jasople.Essay.dto.EssayWriteRequestDto;
import app.jasople.Essay.dto.gptResponseDto;
import app.jasople.User.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface gptService {

    List<EssayResponseDto> getEssay(EssayWriteRequestDto requestDto, User user);

}
