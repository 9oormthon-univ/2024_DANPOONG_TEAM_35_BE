package app.jasople.Essay.service;

import app.jasople.Essay.dto.EssayResponseDto;
import app.jasople.Essay.dto.EssayWriteRequestDto;
import app.jasople.User.entity.User;

import java.util.List;

public interface gptService {

    List<EssayResponseDto> getEssay(EssayWriteRequestDto requestDto, User user);

}
