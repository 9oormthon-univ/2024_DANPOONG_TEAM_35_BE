package app.jasople.Essay.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EssayUpdateRequestDto {

    String title;
    String content;

    @Builder
    public EssayUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

