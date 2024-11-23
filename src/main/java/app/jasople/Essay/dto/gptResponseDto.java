package app.jasople.Essay.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class gptResponseDto {

    private String answer;

    public gptResponseDto(String content) {
        this.answer = content;
    }
}