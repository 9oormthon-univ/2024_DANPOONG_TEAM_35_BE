package app.jasople.Essay.dto;


import app.jasople.Essay.entity.Essay;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class EssayWriteRequestDto {

    private int number;

    private String title;

    private List<Long> experienceList;

    private List<Long> infoList;


}
