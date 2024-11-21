package app.jasople.Keywords.dto;


import app.jasople.Keywords.entity.Keywords;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordResponseDto {


    private Long id;

    private String name;


    @Builder
    public KeywordResponseDto(Keywords keyword) {
        this.id = keyword.getId();
        this.name = keyword.getName();
    }

}
