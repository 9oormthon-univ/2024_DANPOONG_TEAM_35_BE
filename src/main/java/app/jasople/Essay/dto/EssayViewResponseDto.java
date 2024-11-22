package app.jasople.Essay.dto;

import app.jasople.Essay.entity.Essay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class EssayViewResponseDto {

    private Long id;
    private String content;

    private Timestamp createdDate;

    @Builder
    public EssayViewResponseDto(Essay essay) {
        this.id = essay.getId();
        this.content = essay.getContent();
        this.createdDate = essay.getCreatedDate();
    }

}
