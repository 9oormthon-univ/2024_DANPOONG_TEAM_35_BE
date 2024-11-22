package app.jasople.Essay.dto;

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
    public EssayViewResponseDto(Long id, String content, Timestamp createdDate) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
    }

}
