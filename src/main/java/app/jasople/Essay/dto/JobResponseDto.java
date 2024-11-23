package app.jasople.Essay.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobResponseDto {
    @Builder
    public JobResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;


}
