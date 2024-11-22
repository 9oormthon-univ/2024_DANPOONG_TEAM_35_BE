package app.jasople.Essay.dto;

import app.jasople.Essay.entity.Essay;
import app.jasople.User.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EssaySaveRequestDto {

    @Builder
    public EssaySaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private String title;

    private String content;



    public Essay toEntity(User user) {
        return Essay.builder()
                .title(title)
                .content(content)
                .build();
    }

}
