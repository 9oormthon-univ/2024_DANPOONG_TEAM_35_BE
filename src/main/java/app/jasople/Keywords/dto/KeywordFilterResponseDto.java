package app.jasople.Keywords.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordFilterResponseDto {

    String name;
    int keywordsCount;

    @Builder
    public KeywordFilterResponseDto(String name, int keywordsCount) {
        this.name = name;
        this.keywordsCount = keywordsCount;
    }


}
