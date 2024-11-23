package app.jasople.Essay.dto;

import app.jasople.Essay.entity.Essay;
import lombok.Builder;

public class EssayResponseDto {

    private int number; //자소서 질문
    private String content; //자기소개서 내용

    @Builder
    public EssayResponseDto(int number, String content) {
        this.number = number;
        this.content = content;
    }
}
